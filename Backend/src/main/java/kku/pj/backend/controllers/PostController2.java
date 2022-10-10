package kku.pj.backend.controllers;

import kku.pj.backend.dto.PostEntityDto;
import kku.pj.backend.dto.PostEntityInsertableDto;
import kku.pj.backend.dto.PostThumbnailEntityDto;
import kku.pj.backend.entities.ImageEntity;
import kku.pj.backend.entities.PostEntity;
import kku.pj.backend.entities.UserEntity;
import kku.pj.backend.services.IImageService;
import kku.pj.backend.services.IPostService;
import kku.pj.backend.services.IUserService;
import kku.pj.backend.utills.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


// HTTP methods document https://www.restapitutorial.com/lessons/httpmethods.html

@RestController
@RequestMapping("api/v2")
@CrossOrigin("*")
public class PostController2 {

    private final IPostService postService;
    private final IUserService userService;
    private final IImageService imageService;
    private final Author author;

    public PostController2(IPostService postService, IUserService userService, IImageService imageService, Author author) {
        this.postService = postService;
        this.userService = userService;
        this.imageService = imageService;
        this.author = author;
    }


    @GetMapping("posts")
    public String getAllPost(){
        return "It's alot of item please use pagination";
    }


    @GetMapping("posts/{offset}/{size}")
    public Page<PostThumbnailEntityDto> getPosts(
            @PathVariable int offset,
            @PathVariable int size,
            @RequestParam Optional<String> sortBy,
            @RequestParam Optional<Sort.Direction> sortDirection
            ){
        String field = sortBy.orElse("id");
        Sort.Direction direction = sortDirection.orElse(Sort.Direction.DESC);
        Sort sort = Sort.by(direction,field);
        return postService.getThumbnail(offset,size,sort);
    }
//
//
    @GetMapping("post/{id}")
    @ResponseBody
    public ResponseEntity<PostEntityDto> getPost(@PathVariable int id){
        PostEntity post = postService.get(id);
        if(post==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        PostEntityDto postDto = new PostEntityDto(post);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
//
//
    @PostMapping("post")
    public ResponseEntity addPost(
            @RequestBody PostEntityInsertableDto postContentDto,
            @CurrentSecurityContext SecurityContext sContext
    ){
        String username = author.getUsernameFromContext(sContext);
        if(username.equals(author.ANONYMOUS_USER)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        PostEntity post = postContentDto.toEntity();
        post.setUsername(username);

        try {
            postService.add(post);
            post = postService.get(post.getId());
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        PostEntityDto postDto = new PostEntityDto(post);
        return new ResponseEntity<>(postDto,HttpStatus.OK);

    }
//
//
    @PutMapping("post/{id}")
    @ResponseBody
    public ResponseEntity<PostEntityDto> modifyPost(
            @RequestBody PostEntityInsertableDto postContentDto,
            @PathVariable int id
    ){
        try {

            PostEntity post = postService.get(id);
            post = postContentDto.updateEntity(post);
            post = postService.update(post);

            return new ResponseEntity<>(new PostEntityDto(post), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//
//
    @DeleteMapping("post/{id}")
    @ResponseBody
    public ResponseEntity<Object> removePost(@PathVariable int id){
        try {
            PostEntity post = postService.get(id);
            postService.remove(post);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
