package com.melo.vibyn.user.infrastructure.api.dto;

import java.util.UUID;

public record UserDto (

        UUID id,
        String name,
        String nickname,
        String email,
        String status
) {}
