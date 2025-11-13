package com.melo.vibyn.spotify.application.command.savetrack;

import com.melo.vibyn.common.mediator.Request;
import java.util.UUID;

public record SaveTrackRequest (
        UUID userId, String spotifyTrackId) implements Request <SaveTrackResponse> {
}
