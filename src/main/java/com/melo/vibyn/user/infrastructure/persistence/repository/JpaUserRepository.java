package com.melo.vibyn.user.infrastructure.persistence.repository;

import com.melo.vibyn.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository <UserEntity, UUID>{

    Boolean existsByEmail (String email);

    Boolean existsByNickname (String nickname);
}
