package com.melo.vibyn.spotify.infrastructure.persistence.repository.implementation.artist;

import com.melo.vibyn.spotify.domain.entity.ArtistDomain;
import com.melo.vibyn.spotify.domain.port.ArtistRepositoryPort;
import com.melo.vibyn.spotify.infrastructure.persistence.entity.ArtistEntity;
import com.melo.vibyn.spotify.infrastructure.persistence.mapper.ArtistEntityMapper;
import com.melo.vibyn.spotify.infrastructure.persistence.repository.JpaArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArtistRepositoryImpl implements ArtistRepositoryPort {

    private final JpaArtistRepository artistRepository;
    private final ArtistEntityMapper artistMapper;

    @Override
    public ArtistDomain save(ArtistDomain artistDomain) {

        ArtistEntity artistEntity = artistMapper.toEntity(artistDomain);
        ArtistEntity save = artistRepository.save(artistEntity);

        return artistMapper.toDomain(save);
    }

    @Override
    public Optional<ArtistDomain> findBySpotifyId(String spotifyId) {
        return artistRepository.findBySpotifyArtistId(spotifyId)
                .map(artistMapper::toDomain);
    }
}
