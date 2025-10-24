package com.melo.vibyn.post.infrastructure.persistence.mapper;

import com.melo.vibyn.post.domain.entity.Post;
import com.melo.vibyn.post.infrastructure.persistence.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PostEntityMapper {


    @Mapping(target = "userId", source = "creator.id")
    Post toDomain(PostEntity entity);

    @Mapping(target = "creator", ignore = true)
    PostEntity toEntity(Post post);


}
