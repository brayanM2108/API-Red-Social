package com.melo.vibyn.user.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record User (

        UUID id,
        String name,
        String nickname,
        String email,
        String biography,
        String password,
        LocalDateTime createdAt,
        Boolean status
)
{}
