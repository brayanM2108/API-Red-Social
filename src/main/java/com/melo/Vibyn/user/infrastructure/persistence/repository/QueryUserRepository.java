package com.melo.Vibyn.user.infrastructure.persistence.repository;

import com.melo.Vibyn.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryUserRepository extends JpaRepository<UserEntity, Long> {
}
