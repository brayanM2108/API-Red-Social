package com.melo.vibyn.spotify.infrastructure.api.dto;


import java.util.List;

public record TrackDto(
        String id,
        String name,
        String album,
        List<String> artist,
        Integer durationMs,
        String imageUrl
) {}