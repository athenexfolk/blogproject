package kku.pj.backend.services.V1;

import kku.pj.backend.dto.PostContentDto;
import kku.pj.backend.dto.PostThumbnailDto;
import kku.pj.backend.entities.V1.Post;
import kku.pj.backend.services.exceptions.PostIdNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    List<PostThumbnailDto> getPostsTrumbull();

    Optional<Post> getPost(int id);

    Page<PostThumbnailDto> getPosts(int offset, int size, Optional<String> sortByField, Optional<Sort.Direction> sortDirection);

    Post addPost(Post post);

    Post updatePost(int id, PostContentDto postContentDto) throws PostIdNotFoundException;

    void removePost(int id) throws PostIdNotFoundException;
}
