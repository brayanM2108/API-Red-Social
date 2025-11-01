// java
package com.melo.vibyn.comment.application.query.getByPost;

import com.melo.vibyn.comment.infrastructure.api.dto.CommentSummaryViewDto;
import com.melo.vibyn.comment.infrastructure.persistence.repository.QueryCommentRepository;
import com.melo.vibyn.common.mediator.RequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCommentByPostIdHandler implements RequestHandler<GetCommentByPostIdRequest, GetCommentByPostIdResponse> {

    private final QueryCommentRepository commentRepository;

    @Override
    public GetCommentByPostIdResponse handle(GetCommentByPostIdRequest request) {
        Page<CommentSummaryViewDto> comments = commentRepository.findCommentSummaryByPostId(
                request.postId(),
                request.pageable()
        );
        return new GetCommentByPostIdResponse(comments);
    }

    @Override
    public Class<GetCommentByPostIdRequest> getRequestType() {
        return GetCommentByPostIdRequest.class;
    }
}
