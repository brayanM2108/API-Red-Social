package com.melo.vibyn.spotify.infrastructure.api;


import com.melo.vibyn.common.mediator.Mediator;
import com.melo.vibyn.spotify.application.command.savealbum.SaveAlbumRequest;
import com.melo.vibyn.spotify.application.command.savealbum.SaveAlbumResponse;
import com.melo.vibyn.spotify.application.query.searchalbum.SearchAlbumRequest;
import com.melo.vibyn.spotify.application.query.searchalbum.SearchAlbumResponse;
import com.melo.vibyn.spotify.domain.entity.AlbumDomain;
import com.melo.vibyn.spotify.infrastructure.api.dto.AlbumDto;
import com.melo.vibyn.spotify.infrastructure.api.mapper.AlbumMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/spotify/albums")
public class SpotifyAlbumController implements SpotifyAlbumApi {


    private final Mediator mediator;
    private final AlbumMapper albumMapper;

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<AlbumDto>> search(UUID userId, String query) {
        SearchAlbumRequest request = new SearchAlbumRequest(userId, query);
        SearchAlbumResponse response = mediator.dispatch(request);
        return ResponseEntity.ok(response.albums());
    }

    @Override
    @PostMapping
    public ResponseEntity<AlbumDto> save(@RequestParam UUID userId, @RequestParam String albumId) {
        SaveAlbumRequest request = new SaveAlbumRequest(userId, albumId);
        SaveAlbumResponse response = mediator.dispatch(request);

        AlbumDomain album = response.album();
        AlbumDto albumDto = albumMapper.toAlbumDto(album);
        return ResponseEntity.ok(albumDto);
    }
}
