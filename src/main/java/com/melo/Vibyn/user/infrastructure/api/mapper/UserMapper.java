package com.melo.Vibyn.user.infrastructure.api.mapper;

import com.melo.Vibyn.user.application.port.UserMapperPort;
import com.melo.Vibyn.user.domain.entity.User;
import com.melo.Vibyn.user.infrastructure.api.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper extends UserMapperPort {

    UserDto toUserDto (User user);

    List<UserDto> toUserListDto (List<User> user);
}
