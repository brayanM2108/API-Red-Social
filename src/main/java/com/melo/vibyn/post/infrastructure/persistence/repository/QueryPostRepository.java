package com.melo.vibyn.post.infrastructure.persistence.repository;

import com.melo.vibyn.post.infrastructure.persistence.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QueryPostRepository extends JpaRepository <PostEntity, UUID>{
}
