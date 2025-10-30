package com.melo.vibyn.post.infrastructure.persistence.repository.implementation;

import com.melo.vibyn.post.domain.entity.Post;
import com.melo.vibyn.post.domain.port.PostRepository;
import com.melo.vibyn.post.infrastructure.persistence.mapper.PostEntityMapper;
import com.melo.vibyn.post.infrastructure.persistence.repository.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImplementation implements PostRepository {

    private final JpaPostRepository postRepository;
    private final PostEntityMapper postEntityMapper;

    @Override
    public Optional<Post> findById(UUID id) {
        return postRepository.findById(id).map(postEntityMapper::toDomain);
    }

}
