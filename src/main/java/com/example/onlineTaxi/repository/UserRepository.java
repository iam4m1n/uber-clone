package com.example.onlineTaxi.repository;

import com.example.onlineTaxi.model.Users.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
