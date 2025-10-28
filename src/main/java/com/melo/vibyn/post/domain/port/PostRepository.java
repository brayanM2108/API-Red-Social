package com.melo.vibyn.post.domain.port;

import com.melo.vibyn.post.domain.entity.Post;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository {

    Optional<Post> findById(UUID id);

}
