package com.melo.vibyn.spotify.application.command.refresh;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.spotify.domain.exception.SpotifyTokenRefreshException;
import com.melo.vibyn.spotify.domain.port.SpotifyCredentialsPort;
import com.melo.vibyn.spotify.domain.port.TokenEncryptionPort;
import com.melo.vibyn.spotify.infrastructure.config.SpotifyProperties;
import com.melo.vibyn.spotify.infrastructure.persistence.entity.UserSpotifyCredentialsEntity;
import com.melo.vibyn.user.domain.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@Service
public class RefreshSpotifyTokenHandler implements RequestHandler<RefreshSpotifyTokenRequest, String> {

    private final SpotifyProperties props;
    private final TokenEncryptionPort tokenEncryptionService;
    private final SpotifyCredentialsPort credentialsRepo;

    @Override
    public String handle(RefreshSpotifyTokenRequest request) {

        UserSpotifyCredentialsEntity credentials = credentialsRepo.findByUserId(request.userId())
                .orElseThrow(() -> new UserNotFoundException(request.userId()));
        try {


            var decryptedAccessToken = tokenEncryptionService.decrypt(credentials.getAccessToken());

            if (credentials.getAccessTokenExpiresAt().isAfter(Instant.now())) {
                return decryptedAccessToken;
            }

            var spotifyApi = new SpotifyApi.Builder()
                    .setClientId(props.getClientId())
                    .setClientSecret(props.getClientSecret())
                    .build();

            var decryptedRefreshToken = tokenEncryptionService.decrypt(credentials.getRefreshToken());
            spotifyApi.setRefreshToken(decryptedRefreshToken);

            var authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh().build();
            var authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

            var newAccessToken = authorizationCodeCredentials.getAccessToken();
            var expiresIn = authorizationCodeCredentials.getExpiresIn();
            var expiresAt = Instant.now().plusSeconds(expiresIn);

            credentials.setAccessToken(tokenEncryptionService.encrypt(newAccessToken));
            credentials.setAccessTokenExpiresAt(expiresAt);
            credentials.setUpdatedAt(Instant.now());

            credentialsRepo.save(credentials);

            log.info("Access token refreshed successfully for user {}", request.userId());

            return newAccessToken;

        } catch (Exception e) {
            log.error("Error refreshing Spotify token for user {}: {}", request.userId(), e.getMessage(), e);
            throw new SpotifyTokenRefreshException(request.userId());
        }
    }

    @Override
    public Class<RefreshSpotifyTokenRequest> getRequestType() {
        return RefreshSpotifyTokenRequest.class;
    }
}
