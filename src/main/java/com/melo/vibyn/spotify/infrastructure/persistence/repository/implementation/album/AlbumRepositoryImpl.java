package com.melo.vibyn.spotify.infrastructure.persistence.repository.implementation.album;

import com.melo.vibyn.spotify.domain.entity.AlbumDomain;
import com.melo.vibyn.spotify.domain.port.AlbumRepositoryPort;
import com.melo.vibyn.spotify.infrastructure.persistence.entity.AlbumEntity;
import com.melo.vibyn.spotify.infrastructure.persistence.mapper.AlbumEntityMapper;
import com.melo.vibyn.spotify.infrastructure.persistence.repository.JpaAlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AlbumRepositoryImpl implements AlbumRepositoryPort {

    private final JpaAlbumRepository albumRepository;
    private final AlbumEntityMapper albumMapper;

    @Override
    public AlbumDomain save(AlbumDomain albumDomain) {

        AlbumEntity albumEntity = albumMapper.toEntity(albumDomain);
        AlbumEntity save = albumRepository.save(albumEntity);
        return albumMapper.toDomain(save);

    }

    @Override
    public Optional<AlbumDomain> findBySpotifyId(String spotifyId) {
        return albumRepository.findBySpotifyAlbumId(spotifyId)
                .map(albumMapper::toDomain);
    }
}
