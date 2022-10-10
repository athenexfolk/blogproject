package kku.pj.backend.repositories.V1;

import kku.pj.backend.entities.V1.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
