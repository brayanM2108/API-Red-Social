package com.melo.vibyn.user.application.query;

import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import org.springframework.data.domain.Page;

public record GetAllUserResponse (
     Page<UserDto> users
){}
