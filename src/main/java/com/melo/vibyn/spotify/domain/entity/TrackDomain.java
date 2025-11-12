package com.melo.vibyn.spotify.domain.entity;

import java.util.List;
import java.util.UUID;

public record TrackDomain(

        UUID id,
        String spotifyTrackId,
        String name,
        List<String> artist,
        String album,
        String imageUrl,
        String spotifyUrl,
        Integer durationMs,
        String releaseDate,
        Integer popularity
) {}