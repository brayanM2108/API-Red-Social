package com.melo.vibyn.user.infrastructure.persistence.repository.implementation;

import com.melo.vibyn.user.domain.entity.User;
import com.melo.vibyn.user.domain.port.UserRepository;
import com.melo.vibyn.user.infrastructure.persistence.entity.UserEntity;
import com.melo.vibyn.user.infrastructure.persistence.mapper.UserEntityMapper;
import com.melo.vibyn.user.infrastructure.persistence.repository.QueryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final QueryUserRepository userRepository;
    private final UserEntityMapper userMapper;

    @Autowired
    public UserRepositoryImpl(QueryUserRepository userRepository, UserEntityMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAll() {
    return userRepository.findAll().stream()
                .map(userMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity save = userRepository.save(userEntity);
        return userMapper.toDomain(save);
    }
}
