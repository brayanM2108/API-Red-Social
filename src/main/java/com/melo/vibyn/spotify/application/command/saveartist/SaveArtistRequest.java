package com.melo.vibyn.spotify.application.command.saveartist;

import com.melo.vibyn.common.mediator.Request;

import java.util.UUID;

public record SaveArtistRequest(UUID userId, String spotifyArtistId) implements Request <SaveArtistResponse> {
}
