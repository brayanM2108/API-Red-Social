package com.melo.vibyn.post.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record Post (
        UUID id,
        String title,
        String content,
        UUID userId,
        LocalDateTime createdAt,
        Boolean status
)
{}
