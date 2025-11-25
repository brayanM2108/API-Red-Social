package com.melo.vibyn.spotify.application.port;

import com.melo.vibyn.spotify.infrastructure.api.dto.AlbumDto;
import java.util.List;
import java.util.UUID;

public interface SpotifySearchAlbumPort {

    List<AlbumDto> findSearchAlbums(UUID userId, String query);
}
