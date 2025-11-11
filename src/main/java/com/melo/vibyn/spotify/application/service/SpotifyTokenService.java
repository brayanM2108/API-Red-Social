package com.melo.vibyn.spotify.application.service;

import com.melo.vibyn.spotify.application.command.refresh.RefreshSpotifyTokenHandler;
import com.melo.vibyn.spotify.application.command.refresh.RefreshSpotifyTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpotifyTokenService {

    private final RefreshSpotifyTokenHandler refreshSpotifyTokenHandler;

    public String getValidAccessToken(UUID userId) {
        return refreshSpotifyTokenHandler.handle(new RefreshSpotifyTokenRequest(userId));
    }
}