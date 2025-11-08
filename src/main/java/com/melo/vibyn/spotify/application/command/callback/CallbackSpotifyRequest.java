package com.melo.vibyn.spotify.application.command.callback;

import com.melo.vibyn.common.mediator.Request;

import java.util.UUID;

public record CallbackSpotifyRequest(String code, UUID userId) implements Request<String> {
}
