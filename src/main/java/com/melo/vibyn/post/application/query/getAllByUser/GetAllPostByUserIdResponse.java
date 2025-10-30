package com.melo.vibyn.post.application.query.getAllByUser;

import com.melo.vibyn.post.infrastructure.api.dto.PostViewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class GetAllPostByUserIdResponse {

    private Page<PostViewDto> post;
}
