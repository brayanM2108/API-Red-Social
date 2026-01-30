package com.melo.vibyn.post.application.command.create;

import com.melo.vibyn.common.mediator.RequestHandler;
import com.melo.vibyn.post.domain.entity.Post;
import com.melo.vibyn.post.domain.port.PostRepository;
import com.melo.vibyn.spotify.application.command.savealbum.SaveAlbumHandler;
import com.melo.vibyn.spotify.application.command.savealbum.SaveAlbumRequest;
import com.melo.vibyn.spotify.application.command.savealbum.SaveAlbumResponse;
import com.melo.vibyn.spotify.application.command.savetrack.SaveTrackHandler;
import com.melo.vibyn.spotify.application.command.savetrack.SaveTrackRequest;
import com.melo.vibyn.spotify.application.command.savetrack.SaveTrackResponse;
import com.melo.vibyn.spotify.domain.entity.AlbumDomain;
import com.melo.vibyn.spotify.domain.entity.TrackDomain;
import com.melo.vibyn.user.domain.exception.UserNotFoundException;
import com.melo.vibyn.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePostHandler implements RequestHandler<CreatePostRequest, CreatePostResponse> {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final SaveTrackHandler saveTrackHandler;
    private final SaveAlbumHandler saveAlbumHandler;

    @Override
    @Transactional
    public CreatePostResponse handle(CreatePostRequest request) {


        if (!userRepository.existsById(request.userId())) {
            throw new UserNotFoundException(request.userId());
        }

        Set<TrackDomain> tracks = saveTracksForPost(request);
        Set<AlbumDomain> albums = saveAlbumForPost(request);

        Post post = new Post(
                UUID.randomUUID(),
                request.title(),
                request.content(),
                request.userId(),
                LocalDateTime.now(),
                Boolean.TRUE,
                tracks,
                albums
        );

        Post saved = postRepository.save(post);
        return new CreatePostResponse(saved);
    }

    private Set<TrackDomain> saveTracksForPost(CreatePostRequest request) {
        Set<TrackDomain> tracks = new HashSet<>();
        for (String spotifyTrackId : request.spotifyTrackIds()) {
            SaveTrackResponse response = saveTrackHandler.handle(
                    new SaveTrackRequest(request.userId(), spotifyTrackId)
            );
            tracks.add(response.track());
        }
        return tracks;
    }
    private Set<AlbumDomain> saveAlbumForPost(CreatePostRequest request) {
        Set<AlbumDomain> albums = new HashSet<>();
        for (String spotifyAlbumId : request.spotifyAlbumIds()) {
            SaveAlbumResponse response = saveAlbumHandler.handle(
                    new SaveAlbumRequest(request.userId(), spotifyAlbumId)
            );
            albums.add(response.album());
        }
        return albums;
    }

    @Override
    public Class<CreatePostRequest> getRequestType() {
        return CreatePostRequest.class;
    }
}
