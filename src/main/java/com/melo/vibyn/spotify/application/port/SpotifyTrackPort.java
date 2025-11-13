package com.melo.vibyn.spotify.application.port;

import com.melo.vibyn.spotify.domain.entity.TrackDomain;
import java.util.UUID;

public interface SpotifyTrackPort {

    TrackDomain findTrack(UUID userId, String trackId);
}
