package com.melo.vibyn.spotify.infrastructure.persistence.mapper;

import com.melo.vibyn.spotify.domain.entity.TrackDomain;
import com.melo.vibyn.spotify.infrastructure.persistence.entity.TrackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)

public interface TrackEntityMapper {

    TrackEntity toEntity(TrackDomain trackDomain);

    TrackDomain toDomain(TrackEntity trackEntity);
}
