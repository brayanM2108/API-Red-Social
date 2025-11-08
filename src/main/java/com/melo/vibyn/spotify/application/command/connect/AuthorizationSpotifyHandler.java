package com.melo.vibyn.spotify.application.command.connect;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.spotify.infrastructure.config.SpotifyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;


@Service
@RequiredArgsConstructor
public class AuthorizationSpotifyHandler implements RequestHandler <AuthorizationSpotifyRequest, String> {

    private final SpotifyProperties props;

    @Override
    public String handle(AuthorizationSpotifyRequest request) {

        String scopes = "user-read-private user-read-email playlist-read-private" +
                " playlist-read-collaborative user-top-read";

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(props.getClientId())
                .setRedirectUri(props.getRedirectUri())
                .build();

        AuthorizationCodeUriRequest requestUrl = spotifyApi.authorizationCodeUri()
                .state(request.userId().toString())
                .scope(scopes)
                .build();

        return requestUrl.execute().toString();
    }

    @Override
    public Class<AuthorizationSpotifyRequest> getRequestType() {
        return AuthorizationSpotifyRequest.class;
    }
}

