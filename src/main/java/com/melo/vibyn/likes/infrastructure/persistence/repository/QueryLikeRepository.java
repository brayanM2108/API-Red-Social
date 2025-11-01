package com.melo.vibyn.likes.infrastructure.persistence.repository;

import com.melo.vibyn.likes.infrastructure.persistence.entity.post.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface QueryLikeRepository extends JpaRepository<PostLikeEntity, UUID> {

    Long countByPostId (UUID postId);
}
