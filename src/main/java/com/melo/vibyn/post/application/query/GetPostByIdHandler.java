package com.melo.vibyn.post.application.query;

import com.melo.vibyn.mediator.RequestHandler;
import com.melo.vibyn.post.application.port.PostMapperPort;
import com.melo.vibyn.post.domain.entity.Post;
import com.melo.vibyn.post.domain.entity.PostWithCreator;
import com.melo.vibyn.post.domain.port.PostRepository;
import com.melo.vibyn.user.application.port.UserMapperPort;
import com.melo.vibyn.user.domain.entity.User;
import com.melo.vibyn.user.domain.port.UserRepository;
import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPostByIdHandler implements RequestHandler<GetPostByIdRequest, GetPostByIdResponse> {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapperPort postMapper;

    @Override
    public GetPostByIdResponse handle(GetPostByIdRequest request) {
        Post post = postRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + request.getId()));


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

        return new GetPostByIdResponse(postMapper.toPostDto(postWithCreator));

    }

    @Override
    public Class<GetPostByIdRequest> getRequestType() {
        return GetPostByIdRequest.class;
    }
}
