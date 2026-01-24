package com.melo.vibyn.spotify.infrastructure.api.mapper;

import com.melo.vibyn.spotify.domain.entity.ArtistDomain;
import com.melo.vibyn.spotify.infrastructure.api.dto.ArtistDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)

public interface ArtistMapper {

    ArtistDto toArtistDto(ArtistDomain artist);
}
