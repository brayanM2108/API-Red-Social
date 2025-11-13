package com.melo.vibyn.spotify.application.query.searchtrack;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.spotify.application.port.SpotifySearchTrackPort;
import com.melo.vibyn.spotify.infrastructure.api.dto.TrackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchTrackHandler implements RequestHandler<SearchTrackRequest, SearchTrackResponse> {

    private final SpotifySearchTrackPort spotifySearchPort;

    @Override
    public SearchTrackResponse handle(SearchTrackRequest request) {

        List<TrackDto> domainTracks = spotifySearchPort.findSearchTracks(request.userId() ,request.query());
        return new SearchTrackResponse(domainTracks);
    }

    @Override
    public Class<SearchTrackRequest> getRequestType() {
        return SearchTrackRequest.class;
    }
}
