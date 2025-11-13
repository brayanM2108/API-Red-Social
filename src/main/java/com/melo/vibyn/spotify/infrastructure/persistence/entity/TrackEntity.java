package com.melo.vibyn.spotify.infrastructure.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tracks")
public class TrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "spotify_track_id", unique = true)
    private String spotifyTrackId;

    private String name;

    @Column(name = "artist_name")
    private List<String> artist;

    private String album;

    @Column(name = "image_url", length = 1024)
    private String imageUrl;

    @Column(name = "spotify_url", length = 1024)
    private String spotifyUrl;

    @Column(name = "duration_ms")
    private Integer durationMs;

    @Column(name = "release_date")
    private String releaseDate;

    private Integer popularity;
}
