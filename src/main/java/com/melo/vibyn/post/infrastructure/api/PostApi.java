package com.melo.vibyn.post.infrastructure.api;

import com.melo.vibyn.post.infrastructure.api.dto.PostViewDto;
import org.springframework.http.ResponseEntity;

public interface PostApi {

    ResponseEntity <PostViewDto> getPostById (Long id);
}
