package com.melo.vibyn.spotify.infrastructure.api.dto;

public record ArtistDto(
        String id,
        String name,
        Integer followers,
        String imageUrl

) {
}
