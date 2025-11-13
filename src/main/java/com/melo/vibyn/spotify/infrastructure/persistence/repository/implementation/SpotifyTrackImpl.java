package com.melo.vibyn.spotify.infrastructure.persistence.repository.implementation;

import com.melo.vibyn.spotify.application.port.SpotifyTrackPort;
import com.melo.vibyn.spotify.domain.entity.TrackDomain;
import com.melo.vibyn.spotify.infrastructure.config.SpotifyApiFactory;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Component;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SpotifyTrackImpl implements SpotifyTrackPort {

    private final SpotifyApiFactory spotifyApiFactory;

    @Override
    public TrackDomain findTrack(UUID userId, String trackId) {

        SpotifyApi spotifyApi = spotifyApiFactory.createForUser(userId);

        try {
            GetTrackRequest getTrackRequest = spotifyApi.getTrack(trackId)
                    .build();
            Track track = getTrackRequest.execute();

            return new TrackDomain(
                    null,
                    track.getId(),
                    track.getName(),
                    Arrays.stream(track.getArtists())
                            .map(ArtistSimplified::getName)
                            .collect(Collectors.toList()),
                    track.getAlbum().getName(),
                    track.getAlbum().getImages().length > 0
                            ? track.getAlbum().getImages()[0].getUrl()
                            : null,
                    track.getExternalUrls().get("spotify"),
                    track.getDurationMs(),
                    track.getAlbum().getReleaseDate(),
                    track.getPopularity()
            );

        } catch (
                IOException | ParseException | SpotifyWebApiException e) {
            throw new RuntimeException("Error retrieving track: " + e.getMessage(), e);
        }

    }
}
