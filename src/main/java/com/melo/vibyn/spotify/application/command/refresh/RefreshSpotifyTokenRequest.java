package com.melo.vibyn.spotify.application.command.refresh;

import com.melo.vibyn.common.mediator.Request;

import java.util.UUID;

public record RefreshSpotifyTokenRequest(UUID userId) implements Request<String> {
}
