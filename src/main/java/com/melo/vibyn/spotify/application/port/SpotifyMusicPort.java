package com.melo.vibyn.spotify.application.port;

import com.melo.vibyn.spotify.infrastructure.api.dto.TrackDto;
import java.util.List;
import java.util.UUID;

public interface SpotifyMusicPort {

    List<TrackDto> findSearchTracks(UUID userId, String query);
}
