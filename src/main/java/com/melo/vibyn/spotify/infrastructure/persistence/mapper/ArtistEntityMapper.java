package com.melo.vibyn.spotify.infrastructure.persistence.mapper;

import com.melo.vibyn.spotify.domain.entity.ArtistDomain;
import com.melo.vibyn.spotify.infrastructure.persistence.entity.ArtistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)

public interface ArtistEntityMapper {

    ArtistDomain toDomain (ArtistEntity entity);

    ArtistEntity toEntity (ArtistDomain domain);
}
