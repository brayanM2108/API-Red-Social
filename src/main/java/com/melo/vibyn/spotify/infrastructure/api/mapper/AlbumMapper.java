package com.melo.vibyn.spotify.infrastructure.api.mapper;


import com.melo.vibyn.spotify.domain.entity.AlbumDomain;
import com.melo.vibyn.spotify.infrastructure.api.dto.AlbumDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AlbumMapper {

    AlbumDto toAlbumDto(AlbumDomain album);

}
