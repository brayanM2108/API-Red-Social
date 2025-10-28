package com.melo.vibyn.user.application.query;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.user.domain.entity.User;
import com.melo.vibyn.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUserHandler implements RequestHandler<GetAllUserRequest, GetAllUserResponse> {

    private final UserRepository userRepository;

    @Override
    public GetAllUserResponse handle(GetAllUserRequest request) {
        List<User> users = userRepository.findAll();
        return new GetAllUserResponse(users);
    }

    @Override
    public Class<GetAllUserRequest> getRequestType() {
        return GetAllUserRequest.class;
    }
}
