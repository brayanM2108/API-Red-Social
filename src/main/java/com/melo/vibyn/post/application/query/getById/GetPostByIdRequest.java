package com.melo.vibyn.post.application.query.getById;

import com.melo.vibyn.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GetPostByIdRequest implements Request <GetPostByIdResponse> {

    private UUID id;
}
