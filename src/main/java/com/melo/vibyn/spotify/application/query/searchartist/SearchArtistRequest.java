package com.melo.vibyn.spotify.application.query.searchartist;

import com.melo.vibyn.common.mediator.Request;

import java.util.UUID;

public record SearchArtistRequest(
        UUID userId, String query) implements Request<SearchArtistResponse> {
}
