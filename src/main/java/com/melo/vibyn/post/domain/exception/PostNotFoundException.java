package com.melo.vibyn.post.domain.exception;

import java.util.UUID;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(UUID postId) {
        super("the post with id " + postId + " was not found");
    }
}
