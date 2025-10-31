package com.melo.vibyn.likes.infrastructure.api;

import com.melo.vibyn.common.mediator.Mediator;
import com.melo.vibyn.likes.application.query.getByPost.GetLikeByPostIdResponse;
import com.melo.vibyn.likes.application.query.getByPost.GetLikesByPostIdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/likes")
public class LikeController implements LikeApi{

    private final Mediator mediator;

    @Override
    @GetMapping("/count/{postId}")
    public ResponseEntity<Long> countLikesByPostId(@PathVariable UUID postId) {
        GetLikesByPostIdRequest request = new GetLikesByPostIdRequest(postId);
        GetLikeByPostIdResponse response = mediator.dispatch(request);
        return new ResponseEntity<>(response.likeCount(), HttpStatus.OK);
    }



}
