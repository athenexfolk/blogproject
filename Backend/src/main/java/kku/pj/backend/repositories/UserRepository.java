package kku.pj.backend.repositories;

import kku.pj.backend.entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users,String> {
}
