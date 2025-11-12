package com.melo.vibyn.spotify.application.query.searchtrack;

import com.melo.vibyn.spotify.infrastructure.api.dto.TrackDto;
import java.util.List;

public record SearchTrackResponse(List<TrackDto> tracks) {
}
