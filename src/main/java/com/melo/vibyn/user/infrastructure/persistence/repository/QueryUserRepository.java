package com.melo.vibyn.user.infrastructure.persistence.repository;

import com.melo.vibyn.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryUserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByEmail (String email);

    Boolean existsByNickname (String nickname);
}
