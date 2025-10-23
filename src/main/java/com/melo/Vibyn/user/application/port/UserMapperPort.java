package com.melo.Vibyn.user.application.port;

import com.melo.Vibyn.user.domain.entity.User;
import com.melo.Vibyn.user.infrastructure.api.dto.UserDto;

import java.util.List;

public interface UserMapperPort {

    UserDto toUserDto(User user);
    List<UserDto> toUserListDto(List<User> users);
}
