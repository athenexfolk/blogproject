package kku.pj.backend.repositories;

import kku.pj.backend.dto.PostThumbnailEntityDto;
import kku.pj.backend.entities.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository2 extends JpaRepository<PostEntity,Integer> {
//    @Query("select new kku.pj.backend.dto.PostThumbnailEntityDto(p) from PostEntity p ")
    @Query("select new kku.pj.backend.dto.PostThumbnailEntityDto(p) from PostThumbnailEntity p")
    Page<PostThumbnailEntityDto> findAllThumbnail(Pageable pageable);
}
