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
@Table(name = "albums")
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "spotify_album_id", unique = true)
    private String spotifyAlbumId;

    private String albumName;

    @Column(name = "artist_name")
    private List<String> artistNames;

    @Column(name = "image_url", length = 1024)
    private String imageUrl;

    @Column(name = "spotify_url", length = 1024)
    private String spotifyUrl;

    @Column(name = "release_date")
    private String releaseDate;

    private Integer popularity;
}
