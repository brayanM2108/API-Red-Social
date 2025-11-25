package com.melo.vibyn.spotify.application.query.searchalbum;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.spotify.application.port.SpotifySearchAlbumPort;
import com.melo.vibyn.spotify.infrastructure.api.dto.AlbumDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchAlbumHandler implements RequestHandler<SearchAlbumRequest, SearchAlbumResponse> {

    private final SpotifySearchAlbumPort spotifySearchPort;

    @Override
    public SearchAlbumResponse handle(SearchAlbumRequest request) {

        List<AlbumDto> domainTracks = spotifySearchPort.findSearchAlbums(request.userId() ,request.query());
        return new SearchAlbumResponse(domainTracks);
    }

    @Override
    public Class<SearchAlbumRequest> getRequestType() {
        return SearchAlbumRequest.class;
    }
}
