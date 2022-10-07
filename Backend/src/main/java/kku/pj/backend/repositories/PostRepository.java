package kku.pj.backend.repositories;

import kku.pj.backend.models.PostThumbnail;
import kku.pj.backend.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    Page<Post> findAll(Pageable pageable);

    @Query("select new kku.pj.backend.models.PostThumbnail(p.id,p.title,p.create_at,p.user_id) from Post p")
    List<PostThumbnail> findAllThumbnail();

    @Query("select new kku.pj.backend.models.PostThumbnail(p.id,p.title,p.create_at,p.user_id) from Post p")
    Page<PostThumbnail> findAllThumbnail(Pageable pageable);

}
