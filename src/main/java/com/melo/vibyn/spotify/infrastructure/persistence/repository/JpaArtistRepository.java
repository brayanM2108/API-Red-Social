package com.melo.vibyn.spotify.infrastructure.persistence.repository;

import com.melo.vibyn.spotify.infrastructure.persistence.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface JpaArtistRepository extends JpaRepository<ArtistEntity, UUID> {

    Optional<ArtistEntity> findBySpotifyArtistId(String spotifyArtistId);
}
