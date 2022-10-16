package kku.pj.backend.controllers;

import kku.pj.backend.dto.ImageEntityDto;
import kku.pj.backend.entities.ImageEntity;
import kku.pj.backend.services.IImageService;
import kku.pj.backend.utills.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("api/v2")
@CrossOrigin("*")
public class FileUpload2 {
    private final IImageService imageService;
    private final Author author;

    public FileUpload2(IImageService imageService, Author author) {
        this.imageService = imageService;
        this.author = author;
    }

    @PostMapping("upload/base64")
    public ResponseEntity<Object> uploadbase64(@RequestBody String file, @CurrentSecurityContext SecurityContext securityContext){
        String username = author.getUsernameFromContext(securityContext);
        if(username.equals(author.ANONYMOUS_USER))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try {
            String type = file.substring(file.indexOf("image/"),file.indexOf(";base64,"));
            file = file.substring(file.indexOf(";base64,")+8);
            byte[] img = Base64.getDecoder().decode(file);
            String path = imageService.saveImgToFileSystem(img,type);
            System.out.println(path);
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setUsername(username);
            imageEntity.setUrl(path);

            imageEntity = imageService.add(imageEntity);
            return ResponseEntity.ok(new ImageEntityDto(imageEntity));

        } catch (IOException e) {
            return  new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }


    @PostMapping(value = "/upload/image" )
    public ResponseEntity<ImageEntityDto> uplaodImage(
            @RequestBody Optional<MultipartFile> file,
            @RequestBody Optional<String> atl,
            @RequestBody Optional<String> name,
            @CurrentSecurityContext SecurityContext securityContext
    ){

        if(file.isEmpty()) return  new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);

        String type = file.get().getContentType();
        String username = author.getUsernameFromContext(securityContext);

        if(file.isEmpty() || !type.contains("image/"))
            return  new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);

        if(username.equals(author.ANONYMOUS_USER))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try {
            var imgPath = imageService.saveImgToFileSystem(file.get().getBytes(),type);
            ImageEntity img = new ImageEntity();
            img.setUsername(username);
            img.setAlt(atl.orElse(""));
            img.setName(name.orElse(""));
            img.setUrl(imgPath);

            img = imageService.add(img);
            return ResponseEntity.ok(new ImageEntityDto(img));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("delete/image/{id}")
    public ResponseEntity deleteImage(
            @PathVariable Integer id,
            @CurrentSecurityContext SecurityContext context
    ){
        String username = author.getUsernameFromContext(context);
        if(username.equals(author.ANONYMOUS_USER))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        ImageEntity img = imageService.get(id);
        if( img!=null && img.getUsername().equals(username) ){
            imageService.remove(img);
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }


    @GetMapping("image/{offset}/{size}")
    public ResponseEntity<Page<ImageEntityDto>> getMyImg(
            @PathVariable Integer offset,
            @PathVariable Integer size,
            @CurrentSecurityContext SecurityContext context
    ){
        String username = author.getUsernameFromContext(context);

        if(username.equals(author.ANONYMOUS_USER))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        var a = imageService.gets(username,offset,size,sort);

        return new ResponseEntity<>(a, HttpStatus.OK);
    }


}
