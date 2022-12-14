package kku.pj.backend.controllers;

import kku.pj.backend.dto.UserEntityDto;
import kku.pj.backend.dto.UserEntityUpdateableDto;
import kku.pj.backend.dto.UserRegisterDto;
import kku.pj.backend.entities.UserEntity;
import kku.pj.backend.services.IImageService;
import kku.pj.backend.services.IUserService;
import kku.pj.backend.utills.Author;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2")
@CrossOrigin("*")
public class UserController2 {

    private final IUserService userService;
    private final IImageService imageService;
    private final Author author;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController2(IUserService userService, IImageService imageService, Author author, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.imageService = imageService;
        this.author = author;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping("register")
    @ResponseBody
    public ResponseEntity<UserEntityDto> register(
            @RequestBody UserRegisterDto userRegisterDto
    ){
        UserEntity user = userRegisterDto.toEntity();

        if(user.getImgId()!=null && imageService.get(user.getImgId())==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            user = userService.add(user);
            var userDto = new UserEntityDto(user);
            return  ResponseEntity.ok(userDto);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("user/{username}")
    @ResponseBody
    public ResponseEntity<UserEntityDto> profile(@PathVariable String username){
       var user = userService.get(username);
        if(user==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var userDto = new UserEntityDto(user);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
//
//
//
    @GetMapping("myprofile")
    @ResponseBody
    public ResponseEntity<UserEntityDto> profile(
            @CurrentSecurityContext SecurityContext securityContext
    ){
        String username = author.getUsernameFromContext(securityContext) ;
        if(!username.equals(author.ANONYMOUS_USER)) {
            UserEntityDto userDto = new UserEntityDto(userService.get(username));
            return ResponseEntity.ok(userDto);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
//
//
//
    @PutMapping("myprofile")
    @ResponseBody
    public ResponseEntity<UserEntityDto> updateProfile(
            @RequestBody UserEntityUpdateableDto userUpdatableDto,
            @CurrentSecurityContext SecurityContext securityContext
    ){
        String username = author.getUsernameFromContext(securityContext);

        if(username.equals(author.ANONYMOUS_USER))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        UserEntity user = userService.get(username);

        if(user==null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        boolean passwordVerify = bCryptPasswordEncoder.matches(userUpdatableDto.getOld_password(),user.getPassword());
        if(passwordVerify){
            user = userUpdatableDto.updateEntity(user);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user = userService.update(user);
            return new ResponseEntity<>(new UserEntityDto(user),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
