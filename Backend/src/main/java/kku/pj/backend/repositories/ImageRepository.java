package kku.pj.backend.repositories;

import kku.pj.backend.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
