package com.melo.vibyn.spotify.infrastructure.persistence.repository;

import com.melo.vibyn.spotify.infrastructure.persistence.entity.UserSpotifyCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserSpotifyCredentialsRepository extends JpaRepository<UserSpotifyCredentialsEntity, UUID> {

    Optional<UserSpotifyCredentialsEntity> findByUserId(UUID userId);

}
