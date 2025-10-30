package com.melo.vibyn.user.infrastructure.api;

import com.melo.vibyn.user.infrastructure.api.dto.UserCreatedDto;
import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import com.melo.vibyn.user.infrastructure.api.dto.UserRegisterDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserApi {

    ResponseEntity <Page<UserDto>> getAll (int page, int size);

    ResponseEntity <UserCreatedDto> save (@RequestBody UserRegisterDto userRegisterDto);
}
