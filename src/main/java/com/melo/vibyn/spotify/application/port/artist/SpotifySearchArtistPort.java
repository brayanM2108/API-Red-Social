package com.melo.vibyn.spotify.application.port.artist;

import com.melo.vibyn.spotify.infrastructure.api.dto.ArtistDto;

import java.util.List;
import java.util.UUID;

public interface SpotifySearchArtistPort {

    List<ArtistDto> findSearchArtists(UUID userId, String query);

}
