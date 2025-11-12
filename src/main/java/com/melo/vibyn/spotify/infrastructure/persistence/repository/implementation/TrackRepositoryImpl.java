package com.melo.vibyn.spotify.infrastructure.persistence.repository.implementation;

import com.melo.vibyn.spotify.domain.entity.TrackDomain;
import com.melo.vibyn.spotify.domain.port.TrackRepositoryPort;
import com.melo.vibyn.spotify.infrastructure.persistence.entity.TrackEntity;
import com.melo.vibyn.spotify.infrastructure.persistence.mapper.TrackEntityMapper;
import com.melo.vibyn.spotify.infrastructure.persistence.repository.JpaTrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TrackRepositoryImpl implements TrackRepositoryPort {

    private final JpaTrackRepository trackRepository;
    private final TrackEntityMapper trackMapper;

    @Override
    public TrackDomain save(TrackDomain trackDomain) {
        TrackEntity trackEntity = trackMapper.toEntity(trackDomain);
        TrackEntity save = trackRepository.save(trackEntity);
        return trackMapper.toDomain(save);
    }

    @Override
    public Optional<TrackDomain> findBySpotifyId(String spotifyId) {
        return trackRepository.findBySpotifyTrackId(spotifyId)
                .map(trackMapper::toDomain);
    }
}
