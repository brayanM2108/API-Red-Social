package com.melo.vibyn.user.domain.port;

import com.melo.vibyn.user.domain.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();
}
