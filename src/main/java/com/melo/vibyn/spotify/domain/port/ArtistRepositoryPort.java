package com.melo.vibyn.spotify.domain.port;

import com.melo.vibyn.spotify.domain.entity.ArtistDomain;
import java.util.Optional;

public interface ArtistRepositoryPort {

    ArtistDomain save(ArtistDomain artistDomain);

    Optional<ArtistDomain> findBySpotifyId(String spotifyId);
}
