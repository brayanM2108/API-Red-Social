package com.melo.vibyn.comment.infrastructure.api;

import com.melo.vibyn.comment.infrastructure.api.dto.CommentSummaryViewDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CommentApi {

    ResponseEntity <Page<CommentSummaryViewDto>> getCommentById(UUID id, int page, int size);
}
