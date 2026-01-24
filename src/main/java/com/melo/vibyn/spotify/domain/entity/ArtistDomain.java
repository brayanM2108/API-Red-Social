package com.melo.vibyn.spotify.domain.entity;

import java.util.List;
import java.util.UUID;

public record ArtistDomain(

        UUID id,
        String spotifyArtistId,
        String name,
        String imageUrl,
        String spotifyUrl,
        List<String> genres,
        Integer followers,
        Integer popularity
) {
}
