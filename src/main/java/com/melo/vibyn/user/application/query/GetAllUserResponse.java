package com.melo.vibyn.user.application.query;

import com.melo.vibyn.user.domain.entity.User;
import java.util.List;

public record GetAllUserResponse (
     List<User> users
){}
