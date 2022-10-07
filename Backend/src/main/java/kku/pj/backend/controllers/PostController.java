package kku.pj.backend.controllers;

import kku.pj.backend.dto.PostContentDto;
import kku.pj.backend.models.PostThumbnail;
import kku.pj.backend.entities.Post;
import kku.pj.backend.services.PostService;
import kku.pj.backend.services.exceptions.PostIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


// HTTP methods document https://www.restapitutorial.com/lessons/httpmethods.html
@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class PostController {

    final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("posts")
    public List<PostThumbnail> getAllPost(){
        return postService.getPostsTrumbull();
    }


    @GetMapping("posts/{offset}/{size}")
    public Page<PostThumbnail> getPosts(
            @PathVariable int offset,
            @PathVariable int size,
            @RequestParam Optional<String> sortBy,
            @RequestParam Optional<Direction> sortDirection

            ){
        return postService.getPosts(offset,size,sortBy,sortDirection);
    }


    @GetMapping("post/{id}")
    @ResponseBody
    public ResponseEntity<Post> getPost(@PathVariable int id){
        Optional<Post> post = postService.getPost(id);
        if(post.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(post.get(),HttpStatus.OK);
    }


    @PostMapping("post")
    public Post addPost(@RequestBody PostContentDto postContentDto){
        Post post = postContentDto.toPostEntity();
        post = postService.addPost(post);
        return post;
    }


    @PutMapping("post/{id}")
    @ResponseBody
    public ResponseEntity<Post> modifyPost(
            @RequestBody PostContentDto postContentDto,
            @PathVariable int id
    ){
        try {
            Post post = postService.updatePost(id,postContentDto);
            return new ResponseEntity<>(post, HttpStatus.OK);

        } catch (PostIdNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("post/{id}")
    @ResponseBody
    public ResponseEntity<Object> removePost(@PathVariable int id){
        try {
            postService.removePost(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PostIdNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
