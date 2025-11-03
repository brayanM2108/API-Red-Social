package com.melo.vibyn.post.infrastructure.api.mapper;

import com.melo.vibyn.post.application.command.create.CreatePostRequest;
import com.melo.vibyn.post.domain.entity.PostWithCreator;
import com.melo.vibyn.post.infrastructure.api.dto.PostCreateDto;
import com.melo.vibyn.post.infrastructure.api.dto.PostViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)

public interface PostMapper{

    PostViewDto toPostDto(PostWithCreator post);

    CreatePostRequest toCreatePostRequest(PostCreateDto createProductDto);
}
