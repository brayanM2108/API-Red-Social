package com.melo.vibyn.spotify.infrastructure.persistence.repository.implementation.album;

import com.melo.vibyn.spotify.application.port.SpotifyAlbumPort;
import com.melo.vibyn.spotify.domain.entity.AlbumDomain;
import com.melo.vibyn.spotify.infrastructure.config.SpotifyApiFactory;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Component;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SpotifyAlbumImpl implements SpotifyAlbumPort {

    private final SpotifyApiFactory spotifyApiFactory;

    @Override
    public AlbumDomain findAlbum(UUID userId, String albumId) {

        SpotifyApi spotifyApi = spotifyApiFactory.createForUser(userId);

        try {
            GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(albumId)
                    .build();
            Album album = getAlbumRequest.execute();

            return new AlbumDomain(
                    null,
                    album.getId(),
                    album.getExternalUrls().get("spotify"),
                    album.getName(),
                    Arrays.stream(album.getArtists())
                            .map(ArtistSimplified::getName)
                            .collect(Collectors.toList()),
                    album.getImages().length > 0
                            ? album.getImages()[0].getUrl()
                            : null,
                    album.getReleaseDate(),
                    album.getPopularity()
            );

        } catch (
                IOException |
                ParseException |
                SpotifyWebApiException e) {
            throw new RuntimeException("Error retrieving track: " + e.getMessage(), e);
        }

    }
}
