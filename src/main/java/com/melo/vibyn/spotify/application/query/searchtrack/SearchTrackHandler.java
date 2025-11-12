package com.melo.vibyn.spotify.application.query.searchtrack;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.spotify.application.port.SpotifyMusicPort;
import com.melo.vibyn.spotify.infrastructure.api.dto.TrackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchTrackHandler implements RequestHandler<SearchTrackRequest, SearchTrackResponse> {

    private final SpotifyMusicPort spotifyMusicPort;

    @Override
    public SearchTrackResponse handle(SearchTrackRequest request) {

        List<TrackDto> domainTracks = spotifyMusicPort.findSearchTracks(request.userId() ,request.query());
        return new SearchTrackResponse(domainTracks);
    }

    @Override
    public Class<SearchTrackRequest> getRequestType() {
        return SearchTrackRequest.class;
    }
}
