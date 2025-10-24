package com.melo.vibyn.user.infrastructure.api;

import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserApi {

    ResponseEntity <List<UserDto>> getAll ();
}
