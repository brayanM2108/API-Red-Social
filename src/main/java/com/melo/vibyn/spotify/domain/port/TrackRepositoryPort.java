package com.melo.vibyn.spotify.domain.port;

import com.melo.vibyn.spotify.domain.entity.TrackDomain;

import java.util.Optional;

public interface TrackRepositoryPort {

    TrackDomain save (TrackDomain trackDomain);

    Optional<TrackDomain> findBySpotifyId(String spotifyId);

}
