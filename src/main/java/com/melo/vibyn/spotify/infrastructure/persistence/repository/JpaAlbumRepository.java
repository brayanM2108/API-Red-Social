package com.melo.vibyn.spotify.infrastructure.persistence.repository;

import com.melo.vibyn.spotify.infrastructure.persistence.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaAlbumRepository extends JpaRepository<AlbumEntity, UUID> {

    Optional<AlbumEntity> findBySpotifyAlbumId(String spotifyAlbumId);
}
