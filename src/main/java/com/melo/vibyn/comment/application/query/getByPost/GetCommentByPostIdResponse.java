package com.melo.vibyn.comment.application.query.getByPost;

import com.melo.vibyn.comment.infrastructure.api.dto.CommentSummaryViewDto;
import org.springframework.data.domain.Page;

public record GetCommentByPostIdResponse(
        Page<CommentSummaryViewDto> comments
) {}
