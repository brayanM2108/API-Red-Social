package com.melo.vibyn.spotify.application.query.searchtrack;

import com.melo.vibyn.common.mediator.Request;
import java.util.UUID;

public record SearchTrackRequest(
        UUID userId, String query) implements Request<SearchTrackResponse> {
}
