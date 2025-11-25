package com.melo.vibyn.spotify.application.query.searchalbum;

import com.melo.vibyn.common.mediator.Request;

import java.util.UUID;

public record SearchAlbumRequest(
        UUID userId, String query) implements Request<SearchAlbumResponse> {
}
