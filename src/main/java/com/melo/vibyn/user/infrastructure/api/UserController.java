package com.melo.vibyn.user.infrastructure.api;

import com.melo.vibyn.mediator.Mediator;
import com.melo.vibyn.user.application.query.GetAllUserRequest;
import com.melo.vibyn.user.application.query.GetAllUserResponse;
import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController implements UserApi{

    private final Mediator mediator;

    public UserController(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    @GetMapping
    public ResponseEntity <List<UserDto>> getAll() {
        GetAllUserRequest request = new GetAllUserRequest();
        GetAllUserResponse response = mediator.dispatch(request);
        return ResponseEntity.ok(response.getUsers());
    }
}
