package com.melo.vibyn.spotify.infrastructure.api;

import com.melo.vibyn.common.mediator.Mediator;
import com.melo.vibyn.spotify.application.query.searchartist.SearchArtistRequest;
import com.melo.vibyn.spotify.application.query.searchartist.SearchArtistResponse;
import com.melo.vibyn.spotify.infrastructure.api.dto.ArtistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/spotify/artist")
public class SpotifyArtistController implements  SpotifyArtistApi{

    private final Mediator mediator;

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<ArtistDto>> search(@RequestParam UUID userId, @RequestParam String query) {
        SearchArtistRequest request = new SearchArtistRequest(userId, query);
        SearchArtistResponse response = mediator.dispatch(request);

        return ResponseEntity.ok(response.artists());
    }
}
