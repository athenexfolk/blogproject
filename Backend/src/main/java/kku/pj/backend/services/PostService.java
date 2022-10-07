package kku.pj.backend.services;

import kku.pj.backend.dto.PostThumbnailDto;
import kku.pj.backend.entities.Post;
import kku.pj.backend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired PostRepository postRepository;

    public List<PostThumbnailDto> getPostsTrumbull(){
        var posts = postRepository.findAllThumbnail();
        return posts;
    }

    public Optional<Post> getPost(int id){
        var post = postRepository.findById(id);
        return post;
    }

    public Page<PostThumbnailDto> getPosts(int offset, int size, Optional<String> sortByField, Optional<Direction> sortDirection){
        Sort sort = Sort.by(sortDirection.orElse(Direction.DESC), sortByField.orElse("id"));
        Pageable pageable = PageRequest.of(offset, size, sort);

        Page<PostThumbnailDto> posts = postRepository.findAllThumbnail(pageable);
        return posts;
    }

    public Post addPost(Post post){
        post = postRepository.save(post);
        return post;
    }

}
