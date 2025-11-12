package com.melo.vibyn.spotify.infrastructure.api;

import com.melo.vibyn.common.mediator.Mediator;
import com.melo.vibyn.spotify.application.command.callback.CallbackSpotifyRequest;
import com.melo.vibyn.spotify.application.command.connect.AuthorizationSpotifyRequest;
import com.melo.vibyn.spotify.application.query.searchtrack.SearchTrackRequest;
import com.melo.vibyn.spotify.application.query.searchtrack.SearchTrackResponse;
import com.melo.vibyn.spotify.infrastructure.api.dto.TrackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/spotify")
public class SpotifyController implements SpotifyApi{

    private final Mediator mediator;

    @Override
    @GetMapping("/connect")
    public ResponseEntity<String> getUri(@RequestParam UUID userId) {
        String url = mediator.dispatch(new AuthorizationSpotifyRequest(userId));
        return ResponseEntity.ok(url);
    }

    @Override
    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam String code,
                                           @RequestParam("state") UUID userId) {
        String result = mediator.dispatch(new CallbackSpotifyRequest(code, userId));
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<TrackDto>> search(UUID userId, String query) {

        SearchTrackRequest request = new SearchTrackRequest(userId, query);
        SearchTrackResponse response = mediator.dispatch(request);

        return ResponseEntity.ok(response.tracks());
    }


}
