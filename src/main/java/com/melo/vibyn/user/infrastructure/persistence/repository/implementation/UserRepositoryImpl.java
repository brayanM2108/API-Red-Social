package com.melo.vibyn.user.infrastructure.persistence.repository.implementation;

import com.melo.vibyn.user.domain.entity.User;
import com.melo.vibyn.user.domain.port.UserRepository;
import com.melo.vibyn.user.infrastructure.persistence.entity.UserEntity;
import com.melo.vibyn.user.infrastructure.persistence.mapper.UserEntityMapper;
import com.melo.vibyn.user.infrastructure.persistence.repository.JpaUserRepository;
import com.melo.vibyn.user.infrastructure.persistence.repository.QueryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository userRepository;
    private final UserEntityMapper userMapper;

    @Override
    public List<User> findAll() {
    return userRepository.findAll().stream()
                .map(userMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntityRegister(user);
        UserEntity save = userRepository.save(userEntity);
        return userMapper.toDomain(save);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
