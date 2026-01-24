package com.melo.vibyn.spotify.application.command.saveartist;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.spotify.application.port.artist.SpotifyArtistPort;
import com.melo.vibyn.spotify.domain.entity.ArtistDomain;
import com.melo.vibyn.spotify.domain.port.ArtistRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SaveArtistHandler implements RequestHandler<SaveArtistRequest, SaveArtistResponse> {

    private final ArtistRepositoryPort artistRepositoryPort;
    private final SpotifyArtistPort spotifyArtistPort;

    @Override
    public SaveArtistResponse handle(SaveArtistRequest request) {

        Optional<ArtistDomain> existing = artistRepositoryPort.findBySpotifyId(request.spotifyArtistId());
        if (existing.isPresent()) {
            return new SaveArtistResponse(existing.get());
        }

        ArtistDomain artist = spotifyArtistPort.findArtist(request.userId(), request.spotifyArtistId());

        ArtistDomain saved = artistRepositoryPort.save(artist);

        return new SaveArtistResponse(saved);
    }

    @Override
    public Class<SaveArtistRequest> getRequestType() {
        return SaveArtistRequest.class;
    }
}
