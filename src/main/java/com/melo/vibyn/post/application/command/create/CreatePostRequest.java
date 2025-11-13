package com.melo.vibyn.post.application.command.create;

import com.melo.vibyn.common.mediator.Request;
import java.util.Set;
import java.util.UUID;

public record CreatePostRequest (
        String title,
        String content,
        UUID userId,
        Set<String> spotifyTrackIds)
        implements Request<CreatePostResponse>

{}