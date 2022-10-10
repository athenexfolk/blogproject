package kku.pj.backend.services;

import kku.pj.backend.entities.UserEntity;
import kku.pj.backend.repositories.UserRepository2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class UserService2 implements IUserService{
    private final UserRepository2 userRepository;

    public UserService2(UserRepository2 userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity add(UserEntity item) {
        return userRepository.save(item);
    }

    @Override
    public UserEntity get(String s) {
        return userRepository.findById(s).get();
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
}
