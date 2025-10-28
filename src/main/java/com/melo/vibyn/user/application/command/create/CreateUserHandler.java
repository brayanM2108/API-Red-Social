package com.melo.vibyn.user.application.command.create;

import com.melo.vibyn.mediator.RequestHandler;

import com.melo.vibyn.user.domain.entity.User;
import com.melo.vibyn.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CreateUserHandler implements RequestHandler<CreateUserRequest, CreateUserResponse> {

    private final UserRepository userRepository;

    @Override
    public CreateUserResponse handle(CreateUserRequest request) {

        User saved = userRepository.save(request.user());
        return new CreateUserResponse(saved);
    }

    @Override
    public Class<CreateUserRequest> getRequestType() {
        return CreateUserRequest.class;
    }
}
