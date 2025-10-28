package com.melo.vibyn.user.application.command.create;

import com.melo.vibyn.common.mediator.RequestHandler;

import com.melo.vibyn.user.domain.entity.User;
import com.melo.vibyn.user.domain.exception.EmailAlreadyExistsException;
import com.melo.vibyn.user.domain.exception.NicknameAlreadyExistsException;
import com.melo.vibyn.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CreateUserHandler implements RequestHandler<CreateUserRequest, CreateUserResponse> {

    private final UserRepository userRepository;

    @Override
    public CreateUserResponse handle(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.user().email())){
            throw new EmailAlreadyExistsException(request.user().email());
        }

        if (userRepository.existsByNickname(request.user().nickname())){
            throw new NicknameAlreadyExistsException(request.user().nickname());
        }
        
        User saved = userRepository.save(request.user());
        return new CreateUserResponse(saved);
    }

    @Override
    public Class<CreateUserRequest> getRequestType() {
        return CreateUserRequest.class;
    }
}
