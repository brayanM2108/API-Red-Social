package com.melo.Vibyn.user.infrastructure.persistence.repository.implementation;

import com.melo.Vibyn.user.domain.entity.User;
import com.melo.Vibyn.user.domain.port.UserRepository;
import com.melo.Vibyn.user.infrastructure.persistence.mapper.UserEntityMapper;
import com.melo.Vibyn.user.infrastructure.persistence.repository.QueryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                .map(userMapper::toUserEntity)
                .toList();
    }
}
