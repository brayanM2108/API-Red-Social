package com.melo.vibyn.post.infrastructure.api;

import com.melo.vibyn.mediator.Mediator;
import com.melo.vibyn.post.application.query.GetPostByIdRequest;
import com.melo.vibyn.post.application.query.GetPostByIdResponse;
import com.melo.vibyn.post.infrastructure.api.dto.PostViewDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController implements PostApi{

    private final Mediator mediator;

    public PostController(Mediator mediator) {
        this.mediator = mediator;
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PostViewDto> getPostById(@PathVariable("id") Long id) {
        GetPostByIdRequest request = new GetPostByIdRequest(id);
        GetPostByIdResponse response = mediator.dispatch(request);
        return ResponseEntity.ok(response.getPost());
    }
}
