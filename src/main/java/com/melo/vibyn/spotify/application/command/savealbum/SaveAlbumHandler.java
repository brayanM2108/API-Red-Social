package com.melo.vibyn.spotify.application.command.savealbum;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.spotify.application.port.album.SpotifyAlbumPort;
import com.melo.vibyn.spotify.domain.entity.AlbumDomain;
import com.melo.vibyn.spotify.domain.port.AlbumRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SaveAlbumHandler implements RequestHandler<SaveAlbumRequest, SaveAlbumResponse> {

    private final AlbumRepositoryPort albumRepositoryPort;
    private final SpotifyAlbumPort spotifyAlbumPort;

    @Override
    public SaveAlbumResponse handle(SaveAlbumRequest request) {

        Optional<AlbumDomain> existing = albumRepositoryPort.findBySpotifyId(request.spotifyAlbumId());
        if (existing.isPresent()) {
            return new SaveAlbumResponse(existing.get());
        }

        AlbumDomain track = spotifyAlbumPort.findAlbum(request.userId(), request.spotifyAlbumId());

        AlbumDomain saved = albumRepositoryPort.save(track);

        return new SaveAlbumResponse(saved);
    }

    @Override
    public Class<SaveAlbumRequest> getRequestType() {
        return SaveAlbumRequest.class;
    }
}
