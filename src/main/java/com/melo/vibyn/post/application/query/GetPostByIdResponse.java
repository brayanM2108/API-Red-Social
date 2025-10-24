package com.melo.vibyn.post.application.query;

import com.melo.vibyn.post.infrastructure.api.dto.PostViewDto;
import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetPostByIdResponse {

    private PostViewDto post;
}
