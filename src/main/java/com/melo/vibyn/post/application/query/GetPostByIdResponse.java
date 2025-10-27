package com.melo.vibyn.post.application.query;

import com.melo.vibyn.post.domain.entity.PostWithCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPostByIdResponse {

    private PostWithCreator post;
}
