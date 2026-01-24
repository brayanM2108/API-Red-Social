package com.melo.vibyn.spotify.infrastructure.persistence.repository.implementation.artist;

import com.melo.vibyn.spotify.application.port.artist.SpotifySearchArtistPort;
import com.melo.vibyn.spotify.infrastructure.api.dto.ArtistDto;
import com.melo.vibyn.spotify.infrastructure.config.SpotifyApiFactory;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Component;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SpotifySearchArtistImpl implements SpotifySearchArtistPort {

    private final SpotifyApiFactory spotifyApiFactory;

    @Override
    public List<ArtistDto> findSearchArtists(UUID userId, String query) {
        SpotifyApi spotifyApi = spotifyApiFactory.createForUser(userId);
        try {

            SearchArtistsRequest request = spotifyApi.searchArtists(query).limit(10).build();
            Artist[] artists = request.execute().getItems();

            return Arrays.stream(artists)
                    .map(artist -> new ArtistDto(
                            artist.getId(),
                            artist.getName(),
                            artist.getFollowers().getTotal(),
                            artist.getImages().length > 0
                                    ? artist.getImages()[0].getUrl()
                                    : null
                    ))
                    .collect(Collectors.toList());
        } catch (
                IOException |
                SpotifyWebApiException |
                ParseException e) {
            throw new RuntimeException("Error searching albums: " + e.getMessage(), e);
        }
    }


}
