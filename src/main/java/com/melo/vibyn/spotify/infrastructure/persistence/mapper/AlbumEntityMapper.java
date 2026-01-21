package com.melo.vibyn.spotify.infrastructure.persistence.mapper;

import com.melo.vibyn.spotify.domain.entity.AlbumDomain;
import com.melo.vibyn.spotify.infrastructure.persistence.entity.AlbumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)

public interface AlbumEntityMapper {


    @Mapping(target = "artistNames", source = "artist")
    @Mapping(target = "albumName", source = "name")
    AlbumEntity toEntity(AlbumDomain album);

    @Mapping(target = "name", source = "albumName")
    @Mapping(target = "artist", source = "artistNames")
    AlbumDomain toDomain(AlbumEntity albumEntity);
}
