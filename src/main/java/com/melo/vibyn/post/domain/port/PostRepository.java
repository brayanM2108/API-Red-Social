package com.melo.vibyn.post.domain.port;

import com.melo.vibyn.post.domain.entity.Post;

import java.util.Optional;

public interface PostRepository {

    Optional<Post> findById(Long id);



}
