package kku.pj.backend.services;

import kku.pj.backend.entities.UserEntity;
import kku.pj.backend.repositories.UserRepository2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
public class UserService2 implements IUserService ,UserDetailsService{
    private final UserRepository2 userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService2(UserRepository2 userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserEntity add(UserEntity item) {
        item.setPassword(bCryptPasswordEncoder.encode(item.getPassword()));
        return userRepository.save(item);
    }

    @Override
    public UserEntity get(String s) {
        var user = userRepository.findById(s);
        if(user.isEmpty())
            return null;
        return user.get();
    }

    @Override
    public UserEntity update(UserEntity item) {
        return userRepository.save(item);
    }

    @Override
    public boolean remove(UserEntity item) {
        userRepository.delete(item);
        return true;
    }

    @Override
    public Page<UserEntity> gets(int page, int size, Sort sort) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findById(username);

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
