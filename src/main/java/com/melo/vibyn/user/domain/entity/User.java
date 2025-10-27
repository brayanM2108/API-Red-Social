package com.melo.vibyn.user.domain.entity;

import java.time.LocalDateTime;

public record User (

        Long id,
        String name,
        String nickname,
        String email,
        String biography,
        String password,
        LocalDateTime createdAt,
        Boolean status
)
{}
