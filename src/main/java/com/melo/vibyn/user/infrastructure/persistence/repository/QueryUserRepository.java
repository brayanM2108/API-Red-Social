package com.melo.vibyn.user.infrastructure.persistence.repository;

import com.melo.vibyn.user.infrastructure.api.dto.UserDto;
import com.melo.vibyn.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface QueryUserRepository extends JpaRepository<UserEntity, UUID> {


    @Query("""
    SELECT new com.melo.vibyn.user.infrastructure.api.dto.UserDto(
        u.id,
        u.name,
        u.nickname,
        u.email,
        u.createdAt
    )
    FROM UserEntity u
    WHERE u.status = true
    ORDER BY u.createdAt DESC
""")
    Page<UserDto> findAllActive(Pageable pageable);
}
