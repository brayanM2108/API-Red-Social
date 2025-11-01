package com.melo.vibyn.comment.infrastructure.api;

import com.melo.vibyn.comment.application.query.getByPost.GetCommentByPostIdRequest;
import com.melo.vibyn.comment.application.query.getByPost.GetCommentByPostIdResponse;
import com.melo.vibyn.comment.infrastructure.api.dto.CommentSummaryViewDto;
import com.melo.vibyn.common.mediator.Mediator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController implements CommentApi{

    private final Mediator mediator;

    @Override
    @GetMapping("/summary/{id}")
    public ResponseEntity <Page<CommentSummaryViewDto>> getCommentById(@PathVariable UUID id,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        GetCommentByPostIdRequest request = new GetCommentByPostIdRequest(id, pageRequest);
        GetCommentByPostIdResponse response = mediator.dispatch(request);
        return new ResponseEntity<>(response.comments(), HttpStatus.OK);

    }
}
