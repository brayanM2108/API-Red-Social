package com.melo.vibyn.spotify.infrastructure.persistence.repository.implementation.album;

import com.melo.vibyn.spotify.application.port.SpotifySearchAlbumPort;
import com.melo.vibyn.spotify.infrastructure.api.dto.AlbumDto;
import com.melo.vibyn.spotify.infrastructure.config.SpotifyApiFactory;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Component;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SpotifySearchAlbumImpl implements SpotifySearchAlbumPort {

    private final SpotifyApiFactory spotifyApiFactory;

    @Override
    public List<AlbumDto> findSearchAlbums(UUID userId, String query) {
        SpotifyApi spotifyApi = spotifyApiFactory.createForUser(userId);
        try {

            SearchAlbumsRequest request = spotifyApi.searchAlbums(query).limit(10).build();
            AlbumSimplified[] albums = request.execute().getItems();

            return Arrays.stream(albums)
                    .map(album -> new AlbumDto(
                            album.getId(),
                            album.getName(),
                            Arrays.stream(album.getArtists())
                                    .map(ArtistSimplified::getName)
                                    .collect(Collectors.toList()),
                            album.getReleaseDate(),
                            album.getImages().length > 0
                                    ? album.getImages()[0].getUrl()
                                    : null

                    ))
                    .collect(Collectors.toList());
        } catch (
                IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException("Error searching albums: " + e.getMessage(), e);
        }
    }
}
