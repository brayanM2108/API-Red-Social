package com.melo.Vibyn.user.infrastructure.api.dto;

public record UserDto (

        Long id,
        String name,
        String nickname,
        String email,
        String status
) {}
