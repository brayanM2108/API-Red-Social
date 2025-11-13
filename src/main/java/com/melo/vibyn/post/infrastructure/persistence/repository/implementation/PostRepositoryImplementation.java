package com.melo.vibyn.post.infrastructure.persistence.repository.implementation;

import com.melo.vibyn.post.domain.entity.Post;
import com.melo.vibyn.post.domain.port.PostRepository;
import com.melo.vibyn.post.infrastructure.persistence.entity.PostEntity;
import com.melo.vibyn.post.infrastructure.persistence.mapper.PostEntityMapper;
import com.melo.vibyn.post.infrastructure.persistence.repository.JpaPostRepository;
import com.melo.vibyn.spotify.infrastructure.persistence.entity.TrackEntity;
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
                .map(track -> trackRepository.findById(track.id())  // ← Busca en BD
                        .orElseThrow(() -> new RuntimeException("Track not found: " + track.id())))
                .collect(Collectors.toSet());

        postEntity.setTracks(trackEntities);

        PostEntity saved = postRepository.save(postEntity);
        return postEntityMapper.toDomain(saved);
    }

}
