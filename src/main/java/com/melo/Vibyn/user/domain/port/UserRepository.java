package com.melo.Vibyn.user.domain.port;

import com.melo.Vibyn.user.domain.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();
}
