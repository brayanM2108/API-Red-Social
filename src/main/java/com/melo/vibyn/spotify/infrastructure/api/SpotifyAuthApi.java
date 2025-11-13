package com.melo.vibyn.spotify.infrastructure.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.UUID;

public interface SpotifyAuthApi {

    ResponseEntity<String> getUri(@RequestParam UUID userId);

    ResponseEntity<String> callback( @RequestParam String code,
                                     @RequestParam("state") UUID userId);

}
