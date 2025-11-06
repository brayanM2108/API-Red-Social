package com.melo.vibyn.spotify.domain.port;

import com.melo.vibyn.spotify.domain.entity.UserSpotifyCredentials;
import java.util.Optional;
import java.util.UUID;

public interface SpotifyCredentialsPort {

    Optional<UserSpotifyCredentials> findByUserId(UUID userId);

    void save(UserSpotifyCredentials creds);
}
