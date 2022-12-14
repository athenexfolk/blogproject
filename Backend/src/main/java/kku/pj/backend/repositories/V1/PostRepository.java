package kku.pj.backend.repositories.V1;

import kku.pj.backend.dto.v1.PostThumbnailDto;
import kku.pj.backend.entities.V1.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    Page<Post> findAll(Pageable pageable);

    @Query("select new kku.pj.backend.dto.v1.PostThumbnailDto(p.id,p.title,p.create_at,p.user_id) from Post p")
    List<PostThumbnailDto> findAllThumbnail();

    @Query("select new kku.pj.backend.dto.v1.PostThumbnailDto(p.id,p.title,p.create_at,p.user_id) from Post p")
    Page<PostThumbnailDto> findAllThumbnail(Pageable pageable);

}
