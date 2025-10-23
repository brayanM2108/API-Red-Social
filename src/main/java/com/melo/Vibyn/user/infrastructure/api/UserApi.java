package com.melo.Vibyn.user.infrastructure.api;

import com.melo.Vibyn.user.infrastructure.api.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserApi {

    ResponseEntity <List<UserDto>> getAll ();
}
