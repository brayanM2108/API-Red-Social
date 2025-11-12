package com.melo.vibyn.spotify.infrastructure.persistence.port.implementation;

import com.melo.vibyn.spotify.application.port.SpotifyMusicPort;
import com.melo.vibyn.spotify.infrastructure.api.dto.TrackDto;
import com.melo.vibyn.spotify.infrastructure.config.SpotifyApiFactory;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Component;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SpotifyMusicImpl implements SpotifyMusicPort {

    private final SpotifyApiFactory spotifyApiFactory;

    @Override
    public List<TrackDto> findSearchTracks(UUID userId, String query) {
        SpotifyApi spotifyApi = spotifyApiFactory.createForUser(userId);
        try {

            SearchTracksRequest request = spotifyApi.searchTracks(query).limit(10).build();
            Track[] tracks = request.execute().getItems();

            return Arrays.stream(tracks)
                    .map(track -> new TrackDto(
                            track.getId(),
                            track.getName(),
                            track.getAlbum().getName(),
                            Arrays.stream(track.getArtists())
                                    .map(ArtistSimplified::getName)
                                    .collect(Collectors.toList()),
                            track.getDurationMs(),
                            track.getAlbum().getImages().length > 0
                                    ? track.getAlbum().getImages()[0].getUrl()
                                    : null
                    ))
                    .collect(Collectors.toList());
        } catch (
                IOException |
                SpotifyWebApiException |
                ParseException e) {
            throw new RuntimeException("Error searching tracks: " + e.getMessage(), e);
        }
    }
}
