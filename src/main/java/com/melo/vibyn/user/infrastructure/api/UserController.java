package com.melo.vibyn.user.infrastructure.api;

import com.melo.vibyn.mediator.Mediator;
import com.melo.vibyn.user.application.query.GetAllUserRequest;
import com.melo.vibyn.user.application.query.GetAllUserResponse;
import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import com.melo.vibyn.user.infrastructure.api.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController implements UserApi{

    private final Mediator mediator;
    private final UserMapper userMapper;

    public UserController(Mediator mediator, UserMapper userMapper) {
        this.mediator = mediator;
        this.userMapper = userMapper;
    }

    @Override
    @GetMapping
    public ResponseEntity <List<UserDto>> getAll() {
        GetAllUserResponse response = mediator.dispatch(new GetAllUserRequest());
        List<UserDto> users = userMapper.toUserListDto(response.users());
        return ResponseEntity.ok(users);
    }
}
