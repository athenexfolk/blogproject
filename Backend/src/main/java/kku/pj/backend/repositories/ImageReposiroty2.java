package kku.pj.backend.repositories;

import kku.pj.backend.dto.ImageEntityDto;
import kku.pj.backend.entities.ImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageReposiroty2 extends JpaRepository<ImageEntity,Integer> {
    @Query("select new kku.pj.backend.dto.ImageEntityDto(I) from ImageEntity I where I.username = ?1")
    Page<ImageEntityDto> findMyImg(String username,Pageable pageable);
}
