package com.melo.vibyn.spotify.infrastructure.api;

import com.melo.vibyn.spotify.infrastructure.api.dto.ArtistDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface SpotifyArtistApi {

    ResponseEntity <List<ArtistDto>> search(@RequestParam UUID userId, @RequestParam String query);

}
