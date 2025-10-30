package com.melo.vibyn.post.application.query.getAllByUser;

import com.melo.vibyn.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.UUID;
@Data
@AllArgsConstructor
public class GetAllPostByUserIdRequest implements Request<GetAllPostByUserIdResponse> {

    private final UUID userId;
    private final Pageable pageable;
}
