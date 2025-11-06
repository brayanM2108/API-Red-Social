package com.melo.vibyn.spotify.infrastructure.persistence.repository.implementation;

import com.melo.vibyn.spotify.domain.entity.UserSpotifyCredentials;
import com.melo.vibyn.spotify.domain.port.SpotifyCredentialsPort;
import com.melo.vibyn.spotify.infrastructure.persistence.mapper.UserSpotifyCredentialsMapper;
import com.melo.vibyn.spotify.infrastructure.persistence.repository.UserSpotifyCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class UserSpotifyCredentialsImpl implements SpotifyCredentialsPort {

    private final UserSpotifyCredentialsRepository spotifyRepository;
    private final UserSpotifyCredentialsMapper spotifyMapper;

    @Override
    public Optional<UserSpotifyCredentials> findByUserId(UUID userId) {
        return spotifyRepository.findByUserId(userId)
                .map(spotifyMapper::toDomain);
    }

    @Override
    public void save(UserSpotifyCredentials creds) {
        spotifyRepository.save(spotifyMapper.toEntity(creds));
    }
}
