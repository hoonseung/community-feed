package com.backend.user.repository.jpa;

import com.backend.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

}
