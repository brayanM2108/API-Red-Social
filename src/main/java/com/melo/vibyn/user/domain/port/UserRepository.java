package com.melo.vibyn.user.domain.port;

import com.melo.vibyn.user.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    Boolean existsByEmail(String email);

    Boolean existsByNickname(String nickname);
}
