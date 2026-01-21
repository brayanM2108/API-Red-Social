package com.melo.vibyn.spotify.domain.entity;

import java.util.List;
import java.util.UUID;

public record AlbumDomain(
        UUID id,
        String spotifyAlbumId,
        String spotifyUrl,
        String name,
        List<String> artist,
        String imageUrl,
        String releaseDate,
        Integer popularity
) {
}
