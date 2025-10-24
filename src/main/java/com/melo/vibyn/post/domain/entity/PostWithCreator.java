package com.melo.vibyn.post.domain.entity;

import java.time.LocalDateTime;

public record PostWithCreator(
        Long id,
        String title,
        String content,
        Long userId,
        String creatorName,
        LocalDateTime createdAt
) {
}
