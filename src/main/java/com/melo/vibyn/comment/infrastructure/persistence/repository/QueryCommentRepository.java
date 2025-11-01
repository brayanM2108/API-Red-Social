package com.melo.vibyn.comment.infrastructure.persistence.repository;

import com.melo.vibyn.comment.infrastructure.api.dto.CommentSummaryViewDto;
import com.melo.vibyn.comment.infrastructure.persistence.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface QueryCommentRepository extends JpaRepository<CommentEntity, UUID> {


    @Query("""
    SELECT new com.melo.vibyn.comment.infrastructure.api.dto.CommentSummaryViewDto(
        c.id,
        c.content,
        u.nickname,
        COUNT(DISTINCT cl.user)
    )
    FROM CommentEntity c
    JOIN c.user u
    LEFT JOIN CommentLikeEntity cl ON cl.comment.id = c.id
    WHERE c.post.id = :postId
    GROUP BY c.id, c.content, u.nickname
""")
    Page<CommentSummaryViewDto> findCommentSummaryByPostId(@Param("postId") UUID postId, Pageable pageable);

}
