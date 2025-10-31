package com.melo.vibyn.likes.application.query.getByPost;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.likes.infrastructure.persistence.repository.QueryLikeRepository;
import com.melo.vibyn.post.domain.exception.PostNotFoundException;
import com.melo.vibyn.post.infrastructure.persistence.repository.QueryPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLikeByPostIdHandler implements RequestHandler<GetLikesByPostIdRequest, GetLikeByPostIdResponse> {

    private final QueryLikeRepository likeRepository;
    private final QueryPostRepository queryRepository;

    @Override
    public GetLikeByPostIdResponse handle(GetLikesByPostIdRequest request) {

        if (!queryRepository.existsById(request.postId())){
            throw new PostNotFoundException(request.postId());
        }
        Long likes = likeRepository.countByPostId(
                request.postId()
        );
        return new GetLikeByPostIdResponse(likes);
    }

    @Override
    public Class<GetLikesByPostIdRequest> getRequestType() {
        return GetLikesByPostIdRequest.class;
    }
}
