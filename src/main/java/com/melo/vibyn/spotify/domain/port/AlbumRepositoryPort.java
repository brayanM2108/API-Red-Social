package com.melo.vibyn.spotify.domain.port;

import com.melo.vibyn.spotify.domain.entity.AlbumDomain;

import java.util.Optional;

public interface AlbumRepositoryPort {

    AlbumDomain save (AlbumDomain albumDomain);

    Optional<AlbumDomain> findBySpotifyId(String spotifyId);
}
