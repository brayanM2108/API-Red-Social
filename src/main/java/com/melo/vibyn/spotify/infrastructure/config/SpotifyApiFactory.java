package com.melo.vibyn.spotify.infrastructure.config;

import com.melo.vibyn.spotify.application.service.SpotifyTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import se.michaelthelin.spotify.SpotifyApi;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SpotifyApiFactory {

    private final SpotifyTokenService tokenService;

    public SpotifyApi createForUser(UUID userId) {
        String validAccessToken = tokenService.getValidAccessToken(userId);

        return new SpotifyApi.Builder()
                .setAccessToken(validAccessToken)
                .build();
    }
}
