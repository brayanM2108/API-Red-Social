package com.melo.vibyn.user.infrastructure.api.mapper;

import com.melo.vibyn.user.application.command.create.CreateUserRequest;
import com.melo.vibyn.user.domain.entity.User;
import com.melo.vibyn.user.infrastructure.api.dto.UserCreatedDto;
import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    UserDto toUserDto (User user);

    List<UserDto> toUserListDto (List<User> user);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "biography", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toUser(CreateUserRequest request);

    UserCreatedDto toUserCreatedDto(User user);
}
