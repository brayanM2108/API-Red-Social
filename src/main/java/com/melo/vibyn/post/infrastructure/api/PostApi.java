package com.melo.vibyn.post.infrastructure.api;

import com.melo.vibyn.post.infrastructure.api.dto.PostViewDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface PostApi {

    ResponseEntity <PostViewDto> getPostById (UUID id);
}
