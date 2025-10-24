package com.melo.vibyn.post.infrastructure.persistence.repository.implementation;

import com.melo.vibyn.post.domain.entity.Post;
import com.melo.vibyn.post.domain.port.PostRepository;
import com.melo.vibyn.post.infrastructure.persistence.mapper.PostEntityMapper;
import com.melo.vibyn.post.infrastructure.persistence.repository.QueryPostRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostRepositoryImplementation implements PostRepository {

    private final QueryPostRepository postRepository;
    private final PostEntityMapper postEntityMapper;

    public PostRepositoryImplementation(QueryPostRepository postRepository, PostEntityMapper postEntityMapper) {
        this.postRepository = postRepository;
        this.postEntityMapper = postEntityMapper;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id).map(postEntityMapper::toDomain);
    }
}
