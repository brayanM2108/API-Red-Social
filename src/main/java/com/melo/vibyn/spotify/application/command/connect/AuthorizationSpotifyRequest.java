package com.melo.vibyn.spotify.application.command.connect;

import com.melo.vibyn.common.mediator.Request;

import java.util.UUID;

public record AuthorizationSpotifyRequest(UUID userId) implements Request<String> {
}
