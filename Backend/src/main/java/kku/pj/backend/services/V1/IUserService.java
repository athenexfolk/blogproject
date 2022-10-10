package kku.pj.backend.services.V1;

import kku.pj.backend.dto.v1.UserRegisterDto;
import kku.pj.backend.dto.v1.UserUpdatableDto;
import kku.pj.backend.entities.V1.User;
import kku.pj.backend.services.exceptions.UsernameIsExistException;
import kku.pj.backend.services.exceptions.UsernameIsNotExistException;

import java.util.Optional;

public interface IUserService  {
    User addUser(UserRegisterDto userRegisterDto) throws UsernameIsExistException;

    Optional<User> findUser(String username);

    User updateUser(String username, UserUpdatableDto userUpdatableDto) throws UsernameIsNotExistException;
}
