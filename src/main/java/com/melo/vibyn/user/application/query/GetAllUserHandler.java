package com.melo.vibyn.user.application.query;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import com.melo.vibyn.user.infrastructure.persistence.repository.QueryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllUserHandler implements RequestHandler<GetAllUserRequest, GetAllUserResponse> {

    private final QueryUserRepository userRepository;

    @Override
    public GetAllUserResponse handle(GetAllUserRequest request) {
        Page<UserDto> users = userRepository.findAllActive(request.getPageable());
        return new GetAllUserResponse(users);
    }

    @Override
    public Class<GetAllUserRequest> getRequestType() {
        return GetAllUserRequest.class;
    }
}
