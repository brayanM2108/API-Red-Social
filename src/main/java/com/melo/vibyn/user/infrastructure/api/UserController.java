package com.melo.vibyn.user.infrastructure.api;

import com.melo.vibyn.common.mediator.Mediator;
import com.melo.vibyn.post.application.query.getAllByUser.GetAllPostByUserIdRequest;
import com.melo.vibyn.post.application.query.getAllByUser.GetAllPostByUserIdResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController implements UserApi {

    private final Mediator mediator;
    private final UserMapper userMapper;

    @Override
    @GetMapping
    public ResponseEntity<Page<UserDto>> getAll(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        GetAllUserRequest request = new GetAllUserRequest(pageRequest);
        GetAllUserResponse response = mediator.dispatch(request);

        return new ResponseEntity<>(response.users(), HttpStatus.OK);
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
