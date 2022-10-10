package kku.pj.backend.controllers.V1;

import kku.pj.backend.entities.V1.Image;
import kku.pj.backend.services.V1.IImageService;
import kku.pj.backend.utills.Author;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class FileUpload {
    private final IImageService imageService;
    private final Author author;

    public FileUpload(
            @Qualifier("ImageService") IImageService imageService,
            Author author
    ) {
        this.imageService = imageService;
        this.author = author;
    }


    @PostMapping(value = "/upload/image" )
    public ResponseEntity<Image> uplaodImage(
            @RequestParam("image") Optional<MultipartFile> file,
            @RequestParam Optional<String> atl,
            @RequestParam Optional<String> name,
            @CurrentSecurityContext SecurityContext securityContext
    ) {
        String type = file.get().getContentType();
        String username = author.getUsernameFromContext(securityContext);

        if(file.isEmpty() || !type.contains("image/"))
            return  new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);

        if(username == "anonymousUser")
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try {
            var imageRespond = imageService.addImage( file.get().getBytes(), type, name.get(), atl.get(), username );
            return ResponseEntity.ok(imageRespond);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
