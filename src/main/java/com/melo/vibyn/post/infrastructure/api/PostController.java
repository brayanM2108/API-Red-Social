package com.melo.vibyn.post.infrastructure.api;

import com.melo.vibyn.common.mediator.Mediator;
import com.melo.vibyn.post.application.query.getAllByUser.GetAllPostByUserIdRequest;
import com.melo.vibyn.post.application.query.getAllByUser.GetAllPostByUserIdResponse;
import com.melo.vibyn.post.application.query.getById.GetPostByIdRequest;
import com.melo.vibyn.post.application.query.getById.GetPostByIdResponse;
import com.melo.vibyn.post.infrastructure.api.dto.PostViewDto;
import com.melo.vibyn.post.infrastructure.api.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity<Page<PostViewDto>> getPostByUserId(
            @PathVariable("id") UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        GetAllPostByUserIdRequest request = new GetAllPostByUserIdRequest(id, pageRequest);
        GetAllPostByUserIdResponse response = mediator.dispatch(request);

        return new ResponseEntity<>(response.getPost(), HttpStatus.OK);
    }
}
