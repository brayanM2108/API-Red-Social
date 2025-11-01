package com.melo.vibyn.comment.infrastructure.persistence.repository;

import com.melo.vibyn.comment.infrastructure.persistence.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, UUID> {
}
