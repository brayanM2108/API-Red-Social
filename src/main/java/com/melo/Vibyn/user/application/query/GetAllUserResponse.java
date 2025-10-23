package com.melo.Vibyn.user.application.query;

import com.melo.Vibyn.user.infrastructure.api.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllUserResponse {

    private List<UserDto> users;
}
