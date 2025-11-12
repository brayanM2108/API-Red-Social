package com.melo.vibyn.spotify.infrastructure.api;

import com.melo.vibyn.spotify.infrastructure.api.dto.TrackDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.UUID;

public interface SpotifyApi {

    ResponseEntity<String> getUri(@RequestParam UUID userId);

    ResponseEntity<String> callback( @RequestParam String code,
                                     @RequestParam("state") UUID userId);

    ResponseEntity<List<TrackDto>> search(@RequestParam UUID userId, @RequestParam String query);
}
