package com.melo.vibyn.user.application.command.create;

import com.melo.vibyn.common.mediator.Request;
import com.melo.vibyn.user.domain.entity.User;


public record CreateUserRequest(
        User user) implements Request<CreateUserResponse> {
}