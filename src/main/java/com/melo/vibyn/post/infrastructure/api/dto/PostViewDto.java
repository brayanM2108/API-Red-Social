package com.melo.vibyn.post.infrastructure.api.dto;

import java.time.LocalDateTime;

public record PostViewDto(

        Long id,
        String creatorName,
        String title,
        String content,
        LocalDateTime createdAt
) {}
