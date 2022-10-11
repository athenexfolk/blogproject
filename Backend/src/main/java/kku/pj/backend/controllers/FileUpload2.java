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


    @PostMapping(value = "/upload/image" )
    public ResponseEntity<ImageEntityDto> uplaodImage(
            @RequestParam("image") Optional<MultipartFile> file,
            @RequestParam Optional<String> atl,
            @RequestParam Optional<String> name,
            @CurrentSecurityContext SecurityContext securityContext
    ) {
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
