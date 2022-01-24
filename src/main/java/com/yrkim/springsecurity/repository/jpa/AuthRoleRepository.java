package com.yrkim.springsecurity.repository.jpa;

import com.yrkim.springsecurity.model.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRoleRepository extends JpaRepository<AuthRole , Long> {
    Optional<AuthRole> findByRoleName(String roleName);
}
