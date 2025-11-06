package com.melo.vibyn.spotify.infrastructure.persistence.mapper;

import com.melo.vibyn.spotify.domain.entity.UserSpotifyCredentials;
import com.melo.vibyn.spotify.infrastructure.persistence.entity.UserSpotifyCredentialsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserSpotifyCredentialsMapper {

    UserSpotifyCredentialsEntity toEntity(UserSpotifyCredentials creds);

    UserSpotifyCredentials toDomain(UserSpotifyCredentialsEntity credsEntity);
}
