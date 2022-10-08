package kku.pj.backend.controllers;

import kku.pj.backend.services.IImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class FileUpload {
    private final IImageService imageService;

    public FileUpload(
            @Qualifier("ImageService") IImageService imageService
    ) {
        this.imageService = imageService;
    }


    @PostMapping(value = "/upload/image" )
    public ResponseEntity<String> uplaodImage(@RequestParam("image") Optional<MultipartFile> file) {
        if(file.isEmpty()) return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

        String type = file.get().getContentType();
        if(!type.contains("image/")) return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);

        try {
            return ResponseEntity.ok(imageService.byteArrayToImage(file.get().getBytes(),type));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
