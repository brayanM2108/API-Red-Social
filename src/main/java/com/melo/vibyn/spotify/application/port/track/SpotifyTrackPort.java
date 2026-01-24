package com.melo.vibyn.spotify.application.port.track;

import com.melo.vibyn.spotify.domain.entity.TrackDomain;
import java.util.UUID;

public interface SpotifyTrackPort {

    TrackDomain findTrack(UUID userId, String trackId);
}
