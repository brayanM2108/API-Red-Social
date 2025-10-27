package com.melo.vibyn.post.application.port;

import com.melo.vibyn.post.domain.entity.PostWithCreator;
import com.melo.vibyn.post.infrastructure.api.dto.PostViewDto;

public interface PostMapperPort {

    PostViewDto toPostDto(PostWithCreator post);
}
