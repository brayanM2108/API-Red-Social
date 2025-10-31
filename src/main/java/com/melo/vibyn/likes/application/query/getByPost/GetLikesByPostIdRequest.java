package com.melo.vibyn.likes.application.query.getByPost;

import com.melo.vibyn.common.mediator.Request;

import java.util.UUID;

public record GetLikesByPostIdRequest(UUID postId) implements Request<GetLikeByPostIdResponse> {

}
