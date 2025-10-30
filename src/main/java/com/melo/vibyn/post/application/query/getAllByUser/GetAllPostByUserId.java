package com.melo.vibyn.post.application.query.getAllByUser;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.post.infrastructure.api.dto.PostViewDto;
import com.melo.vibyn.post.infrastructure.persistence.repository.QueryPostRepository;

import com.melo.vibyn.user.domain.exception.UserNotFoundException;
import com.melo.vibyn.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllPostByUserId implements RequestHandler<GetAllPostByUserIdRequest, GetAllPostByUserIdResponse> {

    private final QueryPostRepository queryPostRepository;
    private final UserRepository userRepository;

    @Override
    public GetAllPostByUserIdResponse handle(GetAllPostByUserIdRequest request) {

        if (userRepository.findById(request.getUserId()).isEmpty()){
            throw new UserNotFoundException(request.getUserId());
        }
        Page<PostViewDto> posts = queryPostRepository.findByUserId(
                request.getUserId(),
                request.getPageable()
        );
        return new GetAllPostByUserIdResponse(posts);
    }

    @Override
    public Class<GetAllPostByUserIdRequest> getRequestType() {
        return GetAllPostByUserIdRequest.class;
    }
}
