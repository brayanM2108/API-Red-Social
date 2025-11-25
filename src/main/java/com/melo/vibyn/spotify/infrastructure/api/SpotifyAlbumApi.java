package com.melo.vibyn.spotify.infrastructure.api;

import com.melo.vibyn.spotify.infrastructure.api.dto.AlbumDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface SpotifyAlbumApi {

    ResponseEntity<List<AlbumDto>> search(@RequestParam UUID userId,
                                          @RequestParam String query);

    ResponseEntity<AlbumDto> save(@RequestParam UUID userId,
                                  @RequestParam String albumId);
}
