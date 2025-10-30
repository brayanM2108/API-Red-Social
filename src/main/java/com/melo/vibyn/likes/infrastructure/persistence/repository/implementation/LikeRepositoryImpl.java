package com.melo.vibyn.likes.infrastructure.persistence.repository.implementation;

import com.melo.vibyn.likes.domain.port.LikeRepository;
import com.melo.vibyn.likes.infrastructure.persistence.repository.JpaLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    private final JpaLikeRepository likeRepository;
}
