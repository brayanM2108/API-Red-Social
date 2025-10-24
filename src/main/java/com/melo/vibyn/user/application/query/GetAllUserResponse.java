package com.melo.vibyn.user.application.query;

import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllUserResponse {

    private List<UserDto> users;
}
