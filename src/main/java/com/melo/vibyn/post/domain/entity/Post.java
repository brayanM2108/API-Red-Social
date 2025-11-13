package com.melo.vibyn.post.domain.entity;

import com.melo.vibyn.spotify.domain.entity.TrackDomain;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record Post (
        UUID id,
        String title,
        String content,
        UUID userId,
        LocalDateTime createdAt,
        Boolean status,
        Set<TrackDomain>tracks
)
{}
