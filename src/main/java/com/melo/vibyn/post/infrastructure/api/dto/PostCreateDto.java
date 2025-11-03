package com.melo.vibyn.post.infrastructure.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PostCreateDto(

       @NotBlank(message = "the title cannot be blank")
       String title,
       @NotBlank(message = "the content cannot be blank")
       String content,
       @NotNull(message = "the userId cannot be null")
       UUID userId
) {}
