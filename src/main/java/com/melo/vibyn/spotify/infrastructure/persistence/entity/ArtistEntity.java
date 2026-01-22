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
@Table(name = "artist")
public class ArtistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "spotify_artist_id", unique = true)
    private String spotifyArtistId;

    private String name;

    @Column(name = "image_url", length = 1024)
    private List <String> imageUrl;

    @Column(name = "spotify_url", length = 1024)
    private String spotifyUrl;

    private Integer followers;

    private Integer popularity;
}