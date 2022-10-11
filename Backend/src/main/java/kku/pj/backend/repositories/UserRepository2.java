package kku.pj.backend.repositories;

import kku.pj.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository2 extends JpaRepository<UserEntity,String> {
}
