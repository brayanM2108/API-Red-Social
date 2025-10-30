package com.melo.vibyn.post.infrastructure.persistence.repository;

import com.melo.vibyn.post.infrastructure.api.dto.PostViewDto;
import com.melo.vibyn.post.infrastructure.persistence.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface QueryPostRepository extends JpaRepository <PostEntity, UUID>{

    @Query("""
SELECT new com.melo.vibyn.post.infrastructure.api.dto.PostViewDto(
    p.id,
    p.creator.nickname,
    p.title,
    p.content,
    p.createdAt
)
    FROM PostEntity p
    WHERE p.creator.id = :userId
    AND p.status = true
    ORDER BY p.createdAt DESC
    """)
    Page<PostViewDto> findByUserId(@Param("userId") UUID userId, Pageable pageable);


}
