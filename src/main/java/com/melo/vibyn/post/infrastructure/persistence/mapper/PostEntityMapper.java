package com.melo.vibyn.post.infrastructure.persistence.mapper;

import com.melo.vibyn.post.domain.entity.Post;
import com.melo.vibyn.post.infrastructure.persistence.entity.PostEntity;
import com.melo.vibyn.spotify.infrastructure.persistence.mapper.AlbumEntityMapper;
import com.melo.vibyn.spotify.infrastructure.persistence.mapper.TrackEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {AlbumEntityMapper.class, TrackEntityMapper.class})
public interface PostEntityMapper {

    @Mapping(target = "userId", source = "creator.id")
    Post toDomain(PostEntity entity);

    @Mapping(target = "albums", ignore = true)
    @Mapping(target = "tracks", ignore = true)
    @Mapping(target = "creator.id", source = "userId")
    PostEntity toEntity(Post post);



}
