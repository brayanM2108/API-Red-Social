package com.melo.vibyn.spotify.infrastructure.api.dto;


import java.util.List;

public record TrackDto(
        String id,
        String name,
        String albumName,
        List<String> artists,
        Integer durationMs,
        String imageUrl
) {}