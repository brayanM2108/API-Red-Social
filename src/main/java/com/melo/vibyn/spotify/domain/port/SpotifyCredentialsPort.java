package com.melo.vibyn.spotify.domain.port;

import com.melo.vibyn.spotify.infrastructure.persistence.entity.UserSpotifyCredentialsEntity;
import java.util.Optional;
import java.util.UUID;

public interface SpotifyCredentialsPort {

    Optional<UserSpotifyCredentialsEntity> findByUserId(UUID userId);

    void save(UserSpotifyCredentialsEntity userSpotifyCredentialsEntity);
}
