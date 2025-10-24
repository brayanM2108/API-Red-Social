package com.melo.vibyn.user.application.port;

import com.melo.vibyn.user.domain.entity.User;
import com.melo.vibyn.user.infrastructure.api.dto.UserDto;

import java.util.List;

public interface UserMapperPort {

    UserDto toUserDto(User user);
    List<UserDto> toUserListDto(List<User> users);
}
