package com.melo.Vibyn.user.application.query;

import com.melo.Vibyn.mediator.RequestHandler;
import com.melo.Vibyn.user.application.port.UserMapperPort;
import com.melo.Vibyn.user.domain.entity.User;
import com.melo.Vibyn.user.domain.port.UserRepository;
import com.melo.Vibyn.user.infrastructure.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUserHandler implements RequestHandler<GetAllUserRequest, GetAllUserResponse> {

    private final UserRepository userRepository;
    private final UserMapperPort userMapper;

    @Override
    public GetAllUserResponse handle(GetAllUserRequest request) {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = userMapper.toUserListDto(users);
        return new GetAllUserResponse(userDtos);
    }

    @Override
    public Class<GetAllUserRequest> getRequestType() {
        return GetAllUserRequest.class;
    }
}
