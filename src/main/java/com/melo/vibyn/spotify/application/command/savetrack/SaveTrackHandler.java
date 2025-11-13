package com.melo.vibyn.spotify.application.command.savetrack;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.spotify.application.port.SpotifyTrackPort;
import com.melo.vibyn.spotify.domain.entity.TrackDomain;
import com.melo.vibyn.spotify.domain.port.TrackRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SaveTrackHandler implements RequestHandler<SaveTrackRequest, SaveTrackResponse> {

    private final TrackRepositoryPort trackRepositoryPort;
    private final SpotifyTrackPort spotifyTrackPort;

    @Override
    public SaveTrackResponse handle(SaveTrackRequest request) {

        Optional<TrackDomain> existing = trackRepositoryPort.findBySpotifyId(request.spotifyTrackId());
        if (existing.isPresent()) {
            return new SaveTrackResponse(existing.get());
        }

        TrackDomain track = spotifyTrackPort.findTrack(request.userId(), request.spotifyTrackId());

        TrackDomain saved = trackRepositoryPort.save(track);

        return new SaveTrackResponse(saved);
    }

    @Override
    public Class<SaveTrackRequest> getRequestType() {
        return SaveTrackRequest.class;
    }
}
