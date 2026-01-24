package com.melo.vibyn.spotify.infrastructure.api;

import com.melo.vibyn.common.mediator.Mediator;
import com.melo.vibyn.spotify.application.command.saveartist.SaveArtistRequest;
import com.melo.vibyn.spotify.application.command.saveartist.SaveArtistResponse;
import com.melo.vibyn.spotify.application.query.searchartist.SearchArtistRequest;
import com.melo.vibyn.spotify.application.query.searchartist.SearchArtistResponse;
import com.melo.vibyn.spotify.infrastructure.api.dto.ArtistDto;
import com.melo.vibyn.spotify.infrastructure.api.mapper.ArtistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/spotify/artist")
public class SpotifyArtistController implements  SpotifyArtistApi{

    private final Mediator mediator;
    private final ArtistMapper artistMapper;

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<ArtistDto>> search(@RequestParam UUID userId, @RequestParam String artistId) {
        SearchArtistRequest request = new SearchArtistRequest(userId, artistId);
        SearchArtistResponse response = mediator.dispatch(request);

        return ResponseEntity.ok(response.artists());
    }

    @Override
    @PostMapping
    public ResponseEntity<ArtistDto> save(@RequestParam UUID userId, @RequestParam String artistId) {
        SaveArtistRequest request = new SaveArtistRequest(userId, artistId);
        SaveArtistResponse response = mediator.dispatch(request);
        ArtistDto save = artistMapper.toArtistDto(response.artist());

        return ResponseEntity.ok(save);
    }
}
