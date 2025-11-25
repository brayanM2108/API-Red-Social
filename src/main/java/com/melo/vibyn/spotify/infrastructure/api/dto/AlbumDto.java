package com.melo.vibyn.spotify.infrastructure.api.dto;

import java.util.List;

public record AlbumDto (
        String id,
        String name,
        List<String> artist,
        String releaseDate,
        String imageUrl
)
{}
