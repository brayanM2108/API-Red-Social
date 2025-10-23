package com.melo.Vibyn.user.infrastructure.persistence.mapper;

import com.melo.Vibyn.user.domain.entity.User;
import com.melo.Vibyn.user.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserEntityMapper {

    UserEntity toUser (User user);

    User toUserEntity (UserEntity userEntity);
}
