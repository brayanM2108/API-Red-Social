package com.melo.vibyn.spotify.infrastructure.api;


import com.melo.vibyn.common.mediator.Mediator;
import com.melo.vibyn.spotify.application.query.searchalbum.SearchAlbumRequest;
import com.melo.vibyn.spotify.application.query.searchalbum.SearchAlbumResponse;
import com.melo.vibyn.spotify.infrastructure.api.dto.AlbumDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/spotify/albums")
public class SpotifyAlbumController implements SpotifyAlbumApi {


    private final Mediator mediator;


    @Override
    @GetMapping("/search")
    public ResponseEntity<List<AlbumDto>> search(UUID userId, String query) {
        SearchAlbumRequest request = new SearchAlbumRequest(userId, query);
        SearchAlbumResponse response = mediator.dispatch(request);
        return ResponseEntity.ok(response.albums());
    }

    @Override
    public ResponseEntity<AlbumDto> save(UUID userId, String albumId) {
        return null;
    }
}
