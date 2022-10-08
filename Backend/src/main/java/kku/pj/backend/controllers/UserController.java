package kku.pj.backend.controllers;

import kku.pj.backend.dto.UserDto;
import kku.pj.backend.dto.UserRegisterDto;
import kku.pj.backend.dto.UserUpdatableDto;
import kku.pj.backend.entities.User;
import kku.pj.backend.services.IUserService;
import kku.pj.backend.services.exceptions.UsernameIsExistException;
import kku.pj.backend.services.exceptions.UsernameIsNotExistException;
import kku.pj.backend.utills.InitialUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(@Qualifier("UserService") IUserService userService) {
        this.userService = userService;
    }



    @PostMapping("register")
    @ResponseBody
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterDto userRegisterDto){
        try {
            User user = userService.addUser(userRegisterDto);
            return new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
        } catch (UsernameIsExistException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @GetMapping("login")
    public String login(
            @RequestParam Optional<String> username,
            @RequestParam Optional<String> password
    ){
        return String.format("Login as %s %s",username.get(),password.get());
    }



    @GetMapping("user/{username}")
    @ResponseBody
    public ResponseEntity<UserDto> profile(@PathVariable String username){
        Optional<User> user = userService.findUser(username);
        if(user.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new UserDto(user.get()),HttpStatus.OK);
    }



    @GetMapping("myprofile")
    @ResponseBody
    public Object profile(
            @CurrentSecurityContext SecurityContext securityContext
    ){
        String username = getUsernameFromContext(securityContext) ;
        if(username!="anonymousUser") {
            UserDto userDto = new UserDto(userService.findUser(username).get());
            return ResponseEntity.ok(userDto);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }



    @PutMapping("myprofile")
    @ResponseBody
    public ResponseEntity<UserDto> updateProfile(
            @RequestBody UserUpdatableDto userUpdatableDto,
            @CurrentSecurityContext SecurityContext securityContext
    ){
        String username = getUsernameFromContext(securityContext);
        if (username!="anonymousUser"){
            try {
                return new ResponseEntity<>(
                    new UserDto( userService.updateUser(username,userUpdatableDto) ),
                    HttpStatus.OK
                );

            } catch (UsernameIsNotExistException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    private String getUsernameFromContext(SecurityContext securityContext){
        return  (String) securityContext.getAuthentication().getPrincipal();
    }


}
