package kku.pj.backend.controllers;

import kku.pj.backend.entities.Users;
import kku.pj.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class Tmp {
    @Autowired
    UserRepository userRepository;

    @GetMapping("user")
    public ResponseEntity<Iterable<Users>> getAllUser(){
        var data = userRepository.findAll();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("user")
    public ResponseEntity addUser(@RequestBody Users user){
        System.out.println(user.getUsername());

        var a = new Users();
        a.setUsername(user.getUsername());
        userRepository.save(a);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
