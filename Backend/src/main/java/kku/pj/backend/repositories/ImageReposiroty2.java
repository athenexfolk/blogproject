package kku.pj.backend.repositories;

import kku.pj.backend.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageReposiroty2 extends JpaRepository<ImageEntity,Integer> {
}
