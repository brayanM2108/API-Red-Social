package com.melo.vibyn.spotify.infrastructure.api;

import com.melo.vibyn.common.mediator.Mediator;
import com.melo.vibyn.spotify.application.command.savetrack.SaveTrackRequest;
import com.melo.vibyn.spotify.application.command.savetrack.SaveTrackResponse;
import com.melo.vibyn.spotify.application.query.searchtrack.SearchTrackRequest;
import com.melo.vibyn.spotify.application.query.searchtrack.SearchTrackResponse;
import com.melo.vibyn.spotify.domain.entity.TrackDomain;
import com.melo.vibyn.spotify.infrastructure.api.dto.TrackDto;
import com.melo.vibyn.spotify.infrastructure.api.mapper.TrackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/spotify/tracks")
public class SpotifyTrackController implements SpotifyTrackApi{

    private final Mediator mediator;
    private final TrackMapper trackMapper;

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<TrackDto>> search(@RequestParam UUID userId, @RequestParam String query) {

        SearchTrackRequest request = new SearchTrackRequest(userId, query);
        SearchTrackResponse response = mediator.dispatch(request);
        return ResponseEntity.ok(response.tracks());
    }

    @Override
    @PostMapping("/save")
    public ResponseEntity<TrackDto> save(@RequestParam UUID userId, @RequestParam String trackId) {
        SaveTrackRequest request = new SaveTrackRequest(userId, trackId);
        SaveTrackResponse response = mediator.dispatch(request);

        TrackDomain track = response.track();
        TrackDto trackDto = trackMapper.toTrackDto(track);

        return ResponseEntity.ok(trackDto);
    }

}
