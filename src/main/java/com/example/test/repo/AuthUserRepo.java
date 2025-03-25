package com.example.test.repo;

import com.example.test.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepo extends JpaRepository<AuthUser, Long> {
    AuthUser findByUsername(String username);
}
