package com.melo.vibyn.spotify.application.service;

import com.melo.vibyn.common.mediator.Mediator;
import com.melo.vibyn.spotify.application.command.refresh.RefreshSpotifyTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpotifyTokenService {

    private final Mediator mediator;

    public String getValidAccessToken(UUID userId) {
        return mediator.dispatch(new RefreshSpotifyTokenRequest(userId));
    }
}