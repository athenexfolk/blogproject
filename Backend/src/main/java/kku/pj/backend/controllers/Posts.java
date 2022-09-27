package kku.pj.backend.controllers;

import kku.pj.backend.entities.Post;
import kku.pj.backend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api")
public class Posts {

    @Autowired
    PostRepository postRepository;


    @GetMapping("posts")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> getPost(@RequestParam Optional<Integer> id, @RequestParam Optional<Integer> optional_n){

        Integer n = optional_n.orElse(20);

        var data = postRepository.findAll();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("post/{id}")
    public ResponseEntity<Object> getPost(@PathVariable Integer id){
        var post = postRepository.findById(id);
        if(post.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(post.get(),HttpStatus.OK);
        }
    }

    @PostMapping("post")
    public ResponseEntity<Object> addPost(@RequestBody Post post){

        try{
            var a = postRepository.save(post);
            return new ResponseEntity<>(a,HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
