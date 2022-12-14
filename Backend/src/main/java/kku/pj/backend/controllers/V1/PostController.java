package kku.pj.backend.controllers.V1;

import kku.pj.backend.dto.v1.PostContentDto;
import kku.pj.backend.dto.v1.PostThumbnailDto;
import kku.pj.backend.entities.V1.Post;
import kku.pj.backend.services.V1.IPostService;
import kku.pj.backend.services.exceptions.PostIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private final IPostService postService;

    @Autowired
    public PostController(@Qualifier("PostService") IPostService postService) {
        this.postService = postService;
    }


    @GetMapping("posts")
    public List<PostThumbnailDto> getAllPost(){
        return postService.getPostsTrumbull();
    }


    @GetMapping("posts/{offset}/{size}")
    public Page<PostThumbnailDto> getPosts(
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
