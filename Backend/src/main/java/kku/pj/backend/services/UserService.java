package kku.pj.backend.services;

import kku.pj.backend.dto.UserRegisterDto;
import kku.pj.backend.dto.UserUpdatableDto;
import kku.pj.backend.entities.User;
import kku.pj.backend.repositories.UserRepository;
import kku.pj.backend.services.exceptions.UsernameIsExistException;
import kku.pj.backend.services.exceptions.UsernameIsNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User addUser(UserRegisterDto userRegisterDto) throws UsernameIsExistException {
        Optional<User> user = userRepository.findById(userRegisterDto.getUsername());
        if(user.isEmpty()) {
            User encryptPasswordUser = userRegisterDto.toUserEntity();
            encryptPasswordUser.setPassword(bCryptPasswordEncoder.encode(userRegisterDto.getPassword()));

            return userRepository.save(encryptPasswordUser);
        }

        throw new UsernameIsExistException(String.format("Username %s is exist",userRegisterDto.getUsername()));
    }

    @Override
    public Optional<User> findUser(String username){
        return userRepository.findById(username);
    }

    @Override
    public User updateUser(String username, UserUpdatableDto userUpdatableDto) throws UsernameIsNotExistException {
        var user = userRepository.findById(username);
        if(user.isEmpty())
            throw new UsernameIsNotExistException(String.format("Username %s is not exist",username));

        var updateUser = user.get();
        userUpdatableDto.updateToEntity(updateUser);

        return userRepository.save(updateUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException(String.format("User %s not found",username));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));

        return new org.springframework.security.core.userdetails.User(
                user.get().getUsername(),
                user.get().getPassword(),
                authorities
        );
    }
}
