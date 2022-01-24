package com.yrkim.springsecurity.model.dto;

import com.yrkim.springsecurity.model.entity.AuthRole;
import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ADMIN , USER , SUPP;

    @Override
    public String getAuthority() {
        return "ROLE_"+this.name();
    }

    public AuthRole toAuthRole(String roleName) {
        AuthRole authRole = AuthRole.builder()
                .roleName(Roles.valueOf(roleName.toUpperCase()))
                .build();
        return authRole;
    }
}
