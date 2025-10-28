package com.melo.vibyn.user.infrastructure.api;

import com.melo.vibyn.user.infrastructure.api.dto.UserCreatedDto;
import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import com.melo.vibyn.user.infrastructure.api.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserApi {

    ResponseEntity <List<UserDto>> getAll ();

    ResponseEntity <UserCreatedDto> save (@RequestBody UserRegisterDto userRegisterDto);
}
