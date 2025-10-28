package com.melo.vibyn.user.infrastructure.api;

import com.melo.vibyn.common.mediator.Mediator;
import com.melo.vibyn.user.application.command.create.CreateUserRequest;
import com.melo.vibyn.user.application.command.create.CreateUserResponse;
import com.melo.vibyn.user.application.query.GetAllUserRequest;
import com.melo.vibyn.user.application.query.GetAllUserResponse;
import com.melo.vibyn.user.domain.entity.User;
import com.melo.vibyn.user.infrastructure.api.dto.UserCreatedDto;
import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import com.melo.vibyn.user.infrastructure.api.dto.UserRegisterDto;
import com.melo.vibyn.user.infrastructure.api.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController implements UserApi{

    private final Mediator mediator;
    private final UserMapper userMapper;

    @Override
    @GetMapping
    public ResponseEntity <List<UserDto>> getAll() {
        GetAllUserResponse response = mediator.dispatch(new GetAllUserRequest());
        List<UserDto> users = userMapper.toUserListDto(response.users());
        return ResponseEntity.ok(users);
    }

    @Override
    @PostMapping
    public ResponseEntity<UserCreatedDto> save(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        User user = userMapper.toUser(userRegisterDto);
        CreateUserResponse response = mediator.dispatch(new CreateUserRequest(user));
        UserCreatedDto userCreatedDto = userMapper.toUserCreatedDto(response.user());
        return new ResponseEntity<>(userCreatedDto, HttpStatus.CREATED);
    }


}
