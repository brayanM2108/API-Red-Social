package com.melo.vibyn.spotify.domain.exception;

import java.util.UUID;

public class SpotifyTokenRefreshException extends RuntimeException {
    public SpotifyTokenRefreshException(UUID userId) {
        super("Failed to refresh Spotify token for user " + userId);
    }
}
