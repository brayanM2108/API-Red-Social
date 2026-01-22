package com.melo.vibyn.spotify.application.query.searchartist;

import com.melo.vibyn.spotify.infrastructure.api.dto.ArtistDto;

import java.util.List;

public record SearchArtistResponse(List<ArtistDto> artists) {
}
