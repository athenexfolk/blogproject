package kku.pj.backend.services;

import kku.pj.backend.dto.PostContentDto;
import kku.pj.backend.models.PostThumbnail;
import kku.pj.backend.entities.Post;
import kku.pj.backend.repositories.PostRepository;
import kku.pj.backend.services.exceptions.PostIdNotFoundException;
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

    final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostThumbnail> getPostsTrumbull(){
        return postRepository.findAllThumbnail();
    }

    public Optional<Post> getPost(int id){
        return postRepository.findById(id);
    }

    public Page<PostThumbnail> getPosts(int offset, int size, Optional<String> sortByField, Optional<Direction> sortDirection){
        Sort sort = Sort.by(sortDirection.orElse(Direction.DESC), sortByField.orElse("id"));
        Pageable pageable = PageRequest.of(offset, size, sort);

        return postRepository.findAllThumbnail(pageable);
    }

    public Post addPost(Post post){
        post = postRepository.save(post);
        return post;
    }

    public Post updatePost(int id, PostContentDto postContentDto) throws PostIdNotFoundException {
        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty())
            throw new PostIdNotFoundException(String .format("Post ID %d Not Found",id) );

        Post modifiedPost = post.get();
        if(postContentDto.getTitle()!=null)
            modifiedPost.setTitle(postContentDto.getTitle());
        if(postContentDto.getContent()!=null)
            modifiedPost.setContent(postContentDto.getContent());

        return postRepository.save(modifiedPost);
    }


    public void removePost(int id) throws PostIdNotFoundException {
        try{
            postRepository.deleteById(id);
        } catch (Exception e) {
            throw new PostIdNotFoundException(String.format("Post ID %d is not exist.",id));
        }
    }

}
