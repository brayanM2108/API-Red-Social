package com.melo.vibyn.post.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostWithCreator(
        UUID id,
        String title,
        String content,
        UUID userId,
        String creatorName,
        LocalDateTime createdAt
) {
}
