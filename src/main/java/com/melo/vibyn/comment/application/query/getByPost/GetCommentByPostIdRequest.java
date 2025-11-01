package com.melo.vibyn.comment.application.query.getByPost;

import com.melo.vibyn.common.mediator.Request;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

public record GetCommentByPostIdRequest(UUID postId, Pageable pageable) implements Request<GetCommentByPostIdResponse> {

}
