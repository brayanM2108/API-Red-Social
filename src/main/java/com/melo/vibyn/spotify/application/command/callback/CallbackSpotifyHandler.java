package com.melo.vibyn.spotify.application.command.callback;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.spotify.domain.port.SpotifyCredentialsPort;
import com.melo.vibyn.spotify.domain.port.TokenEncryptionPort;
import com.melo.vibyn.spotify.infrastructure.config.SpotifyProperties;
import com.melo.vibyn.spotify.infrastructure.persistence.entity.UserSpotifyCredentialsEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class CallbackSpotifyHandler implements RequestHandler<CallbackSpotifyRequest, String> {

    private final SpotifyProperties props;
    private final SpotifyCredentialsPort credentialsRepo;
    private final TokenEncryptionPort tokenEncryptionService;

    @Override
    public String handle(CallbackSpotifyRequest request) {
        try {
            var spotifyApi = new SpotifyApi.Builder()
                    .setClientId(props.getClientId())
                    .setClientSecret(props.getClientSecret())
                    .setRedirectUri(SpotifyHttpManager.makeUri(props.getRedirectUri().toString()))
                    .build();

            var authorizationCodeRequest = spotifyApi.authorizationCode(request.code()).build();
            var authorizationCodeCredentials = authorizationCodeRequest.execute();

            var accessToken = authorizationCodeCredentials.getAccessToken();
            var refreshToken = authorizationCodeCredentials.getRefreshToken();
            var expiresIn = authorizationCodeCredentials.getExpiresIn();

            var expiresAt = Instant.now().plusSeconds(expiresIn);

            var entityOpt = credentialsRepo.findByUserId(request.userId());

            var entity = entityOpt.orElseGet(() ->
                    new UserSpotifyCredentialsEntity(
                            request.userId(),
                            refreshToken,
                            accessToken,
                            expiresAt,
                            null,
                            Instant.now(),
                            Instant.now()
                    )
            );


            entity.setAccessToken(tokenEncryptionService.encrypt(accessToken));
            entity.setRefreshToken(tokenEncryptionService.encrypt(refreshToken));
            entity.setAccessTokenExpiresAt(expiresAt);
            entity.setUpdatedAt(Instant.now());

            credentialsRepo.save(entity);

            return "Spotify connected successfully";
        } catch (se.michaelthelin.spotify.exceptions.SpotifyWebApiException swae) {
            log.error("SpotifyWebApiException status={} message={}", swae.getCause(), swae.getMessage());
            throw new RuntimeException("Spotify API error: " + swae.getMessage(), swae);
        } catch (java.io.IOException ioe) {
            log.error("IO error when calling Spotify: {}", ioe.getMessage(), ioe);
            throw new RuntimeException("IO error contacting Spotify", ioe);
        } catch (Exception e) {
            log.error("Unexpected error in Spotify callback: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error processing Spotify callback", e);
        }
    }

    @Override
    public Class<CallbackSpotifyRequest> getRequestType() {
        return CallbackSpotifyRequest.class;
    }
}
