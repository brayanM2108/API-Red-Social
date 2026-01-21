package com.melo.vibyn.spotify.application.port;

import com.melo.vibyn.spotify.domain.entity.AlbumDomain;
import java.util.UUID;

public interface SpotifyAlbumPort {

    AlbumDomain findAlbum(UUID userId, String albumId);
}
