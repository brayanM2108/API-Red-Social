package com.melo.vibyn.spotify.application.port.artist;

import com.melo.vibyn.spotify.domain.entity.ArtistDomain;
import java.util.UUID;

public interface SpotifyArtistPort {

    ArtistDomain findArtist(UUID userId, String artistId);
}
