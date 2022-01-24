package com.yrkim.springsecurity.security;

import com.yrkim.springsecurity.model.dto.Roles;
import com.yrkim.springsecurity.model.dto.UserDTO;
import com.yrkim.springsecurity.model.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private UserDTO userDTO;
    private String username;
    private String email;
    private int age;
    private String password;
    private String authority;
    private boolean enabled;
    private Set<Roles> roles;

    public CustomUserDetails(User user) {
        this.userDTO = new UserDTO(user);
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.age = user.getAge();
        this.roles = user.getRoles().stream()
            .map(role -> role.getRoleName())
            .collect(Collectors.toSet());
    }

    // 계정 권한 목록을 리턴 , 미사용
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 계정 만료 여부를 리턴 (true : 만료 안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김 여부를 리턴 (true : 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부를 리턴 (true : 만료 안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부를 리턴 (true : 활성화 됨)
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
