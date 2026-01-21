package com.melo.vibyn.spotify.application.command.savealbum;

import com.melo.vibyn.common.mediator.Request;

import java.util.UUID;

public record SaveAlbumRequest(
        UUID userId, String spotifyAlbumId) implements Request <SaveAlbumResponse> {
}
