package kku.pj.backend.repositories;

import kku.pj.backend.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository2 extends JpaRepository<PostEntity,Integer> {
}
