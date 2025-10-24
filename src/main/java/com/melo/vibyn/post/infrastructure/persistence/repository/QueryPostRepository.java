package com.melo.vibyn.post.infrastructure.persistence.repository;

import com.melo.vibyn.post.infrastructure.persistence.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryPostRepository extends JpaRepository <PostEntity, Long>{
}
