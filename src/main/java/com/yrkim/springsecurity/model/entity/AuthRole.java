package com.yrkim.springsecurity.model.entity;

import com.yrkim.springsecurity.model.dto.Roles;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "authroles")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private Roles roleName;
}
