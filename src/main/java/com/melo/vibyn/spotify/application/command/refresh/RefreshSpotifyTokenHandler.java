package com.melo.vibyn.spotify.application.command.refresh;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.spotify.domain.port.SpotifyCredentialsPort;
import com.melo.vibyn.spotify.infrastructure.config.SpotifyProperties;
import com.melo.vibyn.user.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshSpotifyTokenHandler implements RequestHandler<RefreshSpotifyTokenRequest, String> {

    private final SpotifyProperties props;
    private final SpotifyCredentialsPort credentialsRepo;

    @Override
    public String handle(RefreshSpotifyTokenRequest request) {
        try {
            var credentials = credentialsRepo.findByUserId(request.userId())
                    .orElseThrow(() -> new UserNotFoundException(request.userId()));

            if (credentials.getAccessTokenExpiresAt().isAfter(Instant.now())) {
                return credentials.getAccessToken();
            }

            var spotifyApi = new SpotifyApi.Builder()
                    .setClientId(props.getClientId())
                    .setClientSecret(props.getClientSecret())
                    .build();

            spotifyApi.setRefreshToken(credentials.getRefreshToken());

            var authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh().build();
            var authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

            var newAccessToken = authorizationCodeCredentials.getAccessToken();
            var expiresIn = authorizationCodeCredentials.getExpiresIn();
            var expiresAt = Instant.now().plusSeconds(expiresIn);

            credentials.setAccessToken(newAccessToken);
            credentials.setAccessTokenExpiresAt(expiresAt);
            credentials.setUpdatedAt(Instant.now());

            credentialsRepo.save(credentials);

            log.info("Access token refreshed successfully for user {}", request.userId());
            return newAccessToken;

        } catch (Exception e) {
            log.error("Error refreshing Spotify token for user {}: {}", request.userId(), e.getMessage(), e);
            throw new RuntimeException("Failed to refresh Spotify token", e);
        }
    }

    @Override
    public Class<RefreshSpotifyTokenRequest> getRequestType() {
        return RefreshSpotifyTokenRequest.class;
    }
}
