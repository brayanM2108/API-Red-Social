package com.melo.vibyn.spotify.infrastructure.api.mapper;

import com.melo.vibyn.spotify.domain.entity.TrackDomain;
import com.melo.vibyn.spotify.infrastructure.api.dto.TrackDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)

public interface TrackMapper {

    @Mapping(target = "id", source = "spotifyTrackId")
    TrackDto toTrackDto(TrackDomain track);

}
