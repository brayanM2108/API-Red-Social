package com.melo.vibyn.post.infrastructure.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostViewDto(

        UUID id,
        String creatorName,
        String title,
        String content,
        LocalDateTime createdAt
) {}
