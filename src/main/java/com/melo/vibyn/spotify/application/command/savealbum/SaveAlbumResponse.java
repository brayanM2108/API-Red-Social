package com.melo.vibyn.spotify.application.command.savealbum;

import com.melo.vibyn.spotify.domain.entity.AlbumDomain;

public record SaveAlbumResponse(
        AlbumDomain album) {}
