package com.melo.vibyn.user.infrastructure.api.mapper;

import com.melo.vibyn.user.application.port.UserMapperPort;
import com.melo.vibyn.user.domain.entity.User;
import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper extends UserMapperPort {

    UserDto toUserDto (User user);

    List<UserDto> toUserListDto (List<User> user);
}
