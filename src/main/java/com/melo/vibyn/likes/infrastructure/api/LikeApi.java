package com.melo.vibyn.likes.infrastructure.api;

import org.springframework.http.ResponseEntity;
import java.util.UUID;

public interface LikeApi {

    ResponseEntity <Long> countLikesByPostId(UUID postId);
}
