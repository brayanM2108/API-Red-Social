package com.melo.vibyn.spotify.application.query.searchartist;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.spotify.application.port.SpotifySearchArtistPort;
import com.melo.vibyn.spotify.infrastructure.api.dto.ArtistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchArtistHandler implements RequestHandler<SearchArtistRequest, SearchArtistResponse> {

    private final SpotifySearchArtistPort searchArtistPort;

    @Override
    public SearchArtistResponse handle(SearchArtistRequest request) {

        List<ArtistDto> domainTracks = searchArtistPort.findSearchArtists(request.userId() ,request.query());
        return new SearchArtistResponse(domainTracks);
    }

    @Override
    public Class<SearchArtistRequest> getRequestType() {
        return SearchArtistRequest.class;
    }
}
