package com.melo.vibyn.user.application.query;

import com.melo.vibyn.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GetAllUserRequest implements Request <GetAllUserResponse> {
    Pageable pageable;
}
