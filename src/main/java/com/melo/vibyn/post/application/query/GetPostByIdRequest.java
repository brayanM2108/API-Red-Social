package com.melo.vibyn.post.application.query;

import com.melo.vibyn.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPostByIdRequest implements Request <GetPostByIdResponse> {

    private Long id;
}
