package com.melo.vibyn.comment.infrastructure.api.dto;

import java.util.UUID;

public record CommentSummaryViewDto (

        UUID id,
        String content,
        String creatorName,
        Long likesCount
){}
