package kku.pj.backend.services;

import kku.pj.backend.dto.UserRegisterDto;
import kku.pj.backend.dto.UserUpdatableDto;
import kku.pj.backend.entities.User;
import kku.pj.backend.services.exceptions.UsernameIsExistException;
import kku.pj.backend.services.exceptions.UsernameIsNotExistException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService  {
    User addUser(UserRegisterDto userRegisterDto) throws UsernameIsExistException;

    Optional<User> findUser(String username);

    User updateUser(String username, UserUpdatableDto userUpdatableDto) throws UsernameIsNotExistException;
}
