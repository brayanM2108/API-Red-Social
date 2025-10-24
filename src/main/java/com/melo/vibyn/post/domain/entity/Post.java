package com.melo.vibyn.post.domain.entity;

import java.time.LocalDateTime;

public record Post (
        Long id,
        String title,
        String content,
        Long userId,
        LocalDateTime createdAt,
        Boolean status
)
{}
