package com.melo.vibyn.spotify.infrastructure.persistence.repository.implementation.artist;

import com.melo.vibyn.spotify.application.port.artist.SpotifyArtistPort;
import com.melo.vibyn.spotify.domain.entity.ArtistDomain;
import com.melo.vibyn.spotify.infrastructure.config.SpotifyApiFactory;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Component;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class SpotifyArtistImpl implements SpotifyArtistPort {

    private final SpotifyApiFactory spotifyApiFactory;

    @Override
    public ArtistDomain findArtist(UUID userId, String artistId) {
        SpotifyApi spotifyApi = spotifyApiFactory.createForUser(userId);

        try {
            GetArtistRequest getArtistRequest = spotifyApi.getArtist(artistId)
                    .build();
            Artist artist = getArtistRequest.execute();

            return new ArtistDomain(
                    null,
                    artist.getId(),
                    artist.getName(),
                    artist.getImages().length > 0
                            ? artist.getImages()[0].getUrl()
                            : null,
                    artist.getExternalUrls().get("spotify"),
                    Arrays.stream(artist.getGenres()).toList(),
                    artist.getFollowers().getTotal(),
                    artist.getPopularity()
            );

        } catch (
                IOException |
                ParseException |
                SpotifyWebApiException e) {
            throw new RuntimeException("Error retrieving artist: " + e.getMessage(), e);
        }

    }
}
