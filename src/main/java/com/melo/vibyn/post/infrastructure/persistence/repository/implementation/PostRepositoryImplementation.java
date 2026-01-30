package com.melo.vibyn.post.infrastructure.persistence.repository.implementation;

import com.melo.vibyn.post.domain.entity.Post;
import com.melo.vibyn.post.domain.port.PostRepository;
import com.melo.vibyn.post.infrastructure.persistence.entity.PostEntity;
import com.melo.vibyn.post.infrastructure.persistence.mapper.PostEntityMapper;
import com.melo.vibyn.post.infrastructure.persistence.repository.JpaPostRepository;
import com.melo.vibyn.spotify.infrastructure.persistence.entity.AlbumEntity;
import com.melo.vibyn.spotify.infrastructure.persistence.entity.TrackEntity;
import com.melo.vibyn.spotify.infrastructure.persistence.repository.JpaAlbumRepository;
import com.melo.vibyn.spotify.infrastructure.persistence.repository.JpaTrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImplementation implements PostRepository {

    private final JpaPostRepository postRepository;
    private final PostEntityMapper postEntityMapper;
    private final JpaTrackRepository trackRepository;
    private final JpaAlbumRepository albumRepository;

    @Override
    public Optional<Post> findById(UUID id) {
        return postRepository.findById(id).map(postEntityMapper::toDomain);
    }

    @Override
    public Boolean existsById(UUID id) {
        return postRepository.existsById(id);
    }

    @Override
    public Post save(Post post) {

        PostEntity postEntity = postEntityMapper.toEntity(post);

        Set<TrackEntity> trackEntities = post.tracks().stream()
                .map(track -> trackRepository.findById(track.id())
                        .orElseThrow(() -> new RuntimeException("Track not found: " + track.id())))
                .collect(Collectors.toSet());

        postEntity.setTracks(trackEntities);
        Set<AlbumEntity> albumEntities = post.albums().stream()
                .map(album -> albumRepository.findById(album.id())
                        .orElseThrow(() -> new RuntimeException("Album not found: " + album.id())))
                .collect(Collectors.toSet());
        postEntity.setAlbums(albumEntities);

        PostEntity saved = postRepository.save(postEntity);
        return postEntityMapper.toDomain(saved);
    }

}
