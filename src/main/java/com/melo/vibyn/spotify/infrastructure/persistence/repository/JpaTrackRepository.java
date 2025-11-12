package com.melo.vibyn.spotify.infrastructure.persistence.repository;

import com.melo.vibyn.spotify.infrastructure.persistence.entity.TrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaTrackRepository extends JpaRepository<TrackEntity, UUID> {


    Optional<TrackEntity> findBySpotifyTrackId(String spotifyTrackId);
}
