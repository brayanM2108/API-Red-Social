package com.melo.vibyn.post.application.query.getById;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.post.domain.entity.Post;
import com.melo.vibyn.post.domain.entity.PostWithCreator;
import com.melo.vibyn.post.domain.exception.PostNotFoundException;
import com.melo.vibyn.post.domain.port.PostRepository;
import com.melo.vibyn.user.domain.entity.User;
import com.melo.vibyn.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPostByIdHandler implements RequestHandler<GetPostByIdRequest, GetPostByIdResponse> {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public GetPostByIdResponse handle(GetPostByIdRequest request) {
        Post post = postRepository.findById(request.getId())
                .orElseThrow(() -> new PostNotFoundException(request.getId()));


        User user = userRepository.findById(post.userId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + post.userId()));


        PostWithCreator postWithCreator = new PostWithCreator(
                post.id(),
                post.title(),
                post.content(),
                post.userId(),
                user.name(),
                post.createdAt()
        );

        return new GetPostByIdResponse(postWithCreator);

    }

    @Override
    public Class<GetPostByIdRequest> getRequestType() {
        return GetPostByIdRequest.class;
    }
}
