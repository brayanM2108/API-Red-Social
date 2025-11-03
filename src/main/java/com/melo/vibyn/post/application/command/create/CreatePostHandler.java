package com.melo.vibyn.post.application.command.create;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.post.domain.entity.Post;
import com.melo.vibyn.post.domain.port.PostRepository;
import com.melo.vibyn.user.domain.exception.UserNotFoundException;
import com.melo.vibyn.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePostHandler implements RequestHandler<CreatePostRequest, CreatePostResponse> {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CreatePostResponse handle(CreatePostRequest request) {

        if (!userRepository.existsById(request.userId())) {
            throw new UserNotFoundException(request.userId());
        }
        Post post = new Post(
                UUID.randomUUID(),
                request.title(),
                request.content(),
                request.userId(),
                LocalDateTime.now(),
                Boolean.TRUE
        );

        Post saved = postRepository.save(post);

        return new CreatePostResponse(saved);

    }

    @Override
    public Class<CreatePostRequest> getRequestType() {
        return CreatePostRequest.class;
    }
}
