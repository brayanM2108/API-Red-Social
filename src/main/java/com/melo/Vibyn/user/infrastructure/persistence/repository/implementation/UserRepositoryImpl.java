package com.melo.Vibyn.user.infrastructure.persistence.repository.implementation;

import com.melo.Vibyn.user.domain.entity.User;
import com.melo.Vibyn.user.domain.port.UserRepository;
import com.melo.Vibyn.user.infrastructure.persistence.repository.QueryUserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final QueryUserRepository queryUserRepository;
    private final UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return List.of();
    }
}
