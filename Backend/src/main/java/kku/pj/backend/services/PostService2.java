package kku.pj.backend.services;

import kku.pj.backend.entities.PostEntity;
import kku.pj.backend.repositories.PostRepository2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        return postRepository.findById(integer).get();
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
}
