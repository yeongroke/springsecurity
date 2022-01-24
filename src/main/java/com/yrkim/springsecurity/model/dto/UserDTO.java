package com.yrkim.springsecurity.model.dto;

import com.yrkim.springsecurity.model.entity.AuthRole;
import com.yrkim.springsecurity.model.entity.User;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserDTO extends BaseTimeDTO {
    private Long id;
    private String username;
    private String email;
    private int age;
    private String password;
    private Set<Roles> roles;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.password = user.getPassword();
        this.roles = new HashSet<>();
        user.getRoles()
                .forEach(element -> roles.add(element.getRoleName()));
    }

    public User toUser(UserDTO userDTO) {
        Set<AuthRole> authRoles = userDTO.getRoles()
                .stream()
                .map(val -> {
                    return val.toAuthRole(val.name());
                }).collect(Collectors.toSet());

        User user = User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .age(userDTO.getAge())
                .password(userDTO.getPassword())
                .roles(authRoles)
                .build();
        return user;
    }
}
