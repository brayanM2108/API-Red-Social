package com.melo.vibyn.spotify.domain.entity;

import java.time.Instant;
import java.util.UUID;

public record UserSpotifyCredentials(
        UUID userId,
        String refreshToken,
        String accessToken,
        Instant accessTokenExpiresAt,
        String scopes,
        Instant connectedAt,
        Instant updatedAt
) {}
