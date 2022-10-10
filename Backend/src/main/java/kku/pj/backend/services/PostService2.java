package kku.pj.backend.services;

import kku.pj.backend.dto.PostThumbnailEntityDto;
import kku.pj.backend.entities.PostEntity;
import kku.pj.backend.repositories.PostRepository2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService2 implements IPostService{
    private final PostRepository2 postRepository;

    public PostService2(PostRepository2 postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public PostEntity add(PostEntity item) {
        return postRepository.save(item);
    }

    @Override
    public PostEntity get(Integer integer) {
        Optional<PostEntity> post = postRepository.findById(integer);
        if(post.isEmpty())
            return null;
        return post.get();
    }

    @Override
    public PostEntity update(PostEntity item) {
        return postRepository.save(item);
    }

    @Override
    public boolean remove(PostEntity item) {
         postRepository.delete(item);
         return true;
    }

    @Override
    public Page<PostEntity> gets(int page, int size, Sort sort) {
        return null;
    }

    @Override
    public Page<PostThumbnailEntityDto> getThumbnail(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page,size,sort);
        System.out.println(postRepository.findAllThumbnail(pageable));
        return postRepository.findAllThumbnail(pageable);
    }

}
