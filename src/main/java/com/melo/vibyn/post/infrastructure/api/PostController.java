package com.melo.vibyn.post.infrastructure.api;

import com.melo.vibyn.common.mediator.Mediator;
import com.melo.vibyn.post.application.query.GetPostByIdRequest;
import com.melo.vibyn.post.application.query.GetPostByIdResponse;
import com.melo.vibyn.post.infrastructure.api.dto.PostViewDto;
import com.melo.vibyn.post.infrastructure.api.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController implements PostApi{

    private final Mediator mediator;
    private final PostMapper postMapper;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PostViewDto> getPostById(@PathVariable("id") UUID id) {
        GetPostByIdRequest request = new GetPostByIdRequest(id);
        GetPostByIdResponse response = mediator.dispatch(request);

        PostViewDto post = postMapper.toPostDto(response.getPost());
        return ResponseEntity.ok(post);
    }
}
