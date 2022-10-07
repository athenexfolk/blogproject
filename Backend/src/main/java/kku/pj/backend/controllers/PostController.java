package kku.pj.backend.controllers;

import kku.pj.backend.dto.PostThumbnailDto;
import kku.pj.backend.entities.Post;
import kku.pj.backend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class PostController {

    @Autowired PostService postService;


    @GetMapping("posts")
    public List<PostThumbnailDto> getAllPost(){
        var posts = postService.getPostsTrumbull();
        return posts;
    }


    @GetMapping("posts/{offset}/{size}")
    public Page<PostThumbnailDto> getPosts(
            @PathVariable int offset,
            @PathVariable int size,
            @RequestParam Optional<String> sortBy,
            @RequestParam Optional<Direction> sortDirection

            ){
        var posts = postService.getPosts(offset,size,sortBy,sortDirection);
        return posts;
    }


    @GetMapping("post/{id}")
    @ResponseBody
    public ResponseEntity getPost(@PathVariable int id){
        var post = postService.getPost(id);
        if(post.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(post.get(),HttpStatus.OK);
    }


    @PostMapping("post")
    public Post addPost(@RequestBody Post post){
        post = postService.addPost(post);
        return post;
    }

}
