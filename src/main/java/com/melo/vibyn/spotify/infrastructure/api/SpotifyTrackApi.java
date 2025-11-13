package com.melo.vibyn.spotify.infrastructure.api;

import com.melo.vibyn.spotify.infrastructure.api.dto.TrackDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface SpotifyTrackApi {

    ResponseEntity<List<TrackDto>>search(@RequestParam UUID userId,
                                          @RequestParam String query);

    ResponseEntity<TrackDto> save(@RequestParam UUID userId,
                                  @RequestParam String trackId);
}
