package com.yrkim.springsecurity.repository.jpa;

import com.yrkim.springsecurity.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoy extends JpaRepository<User , Long> {
    Optional<User> findByUsername(String username);
    int countByUsername(String username);
}
