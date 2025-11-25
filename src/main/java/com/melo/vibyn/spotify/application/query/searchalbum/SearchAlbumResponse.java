package com.melo.vibyn.spotify.application.query.searchalbum;

import com.melo.vibyn.spotify.infrastructure.api.dto.AlbumDto;
import java.util.List;

public record SearchAlbumResponse(List<AlbumDto> albums) {
}
