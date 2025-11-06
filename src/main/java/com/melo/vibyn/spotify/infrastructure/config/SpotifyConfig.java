package com.melo.vibyn.spotify.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.michaelthelin.spotify.SpotifyApi;

@Configuration
@RequiredArgsConstructor
public class SpotifyConfig {

    private final SpotifyProperties props;

    @Bean
    public SpotifyApi spotifyApi() {
        return new SpotifyApi.Builder()
                .setClientId(props.getClientId())
                .setClientSecret(props.getClientSecret())
                .setRedirectUri(props.getRedirectUri())
                .build();
    }
}