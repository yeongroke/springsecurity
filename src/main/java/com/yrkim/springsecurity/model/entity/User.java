package com.yrkim.springsecurity.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yrkim.springsecurity.utils.ValidationPattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<AuthRole> roles = new HashSet<>();

    @Column(name = "create_date")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime createDate;

    @Column(name = "update_date")
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime updateDate;



    @Transient
    @JsonIgnore
    private static final int MAX_PASSWORD_LENGTH = 21;

    @Transient
    @JsonIgnore
    private static final int MIN_PASSWORD_LENGTH = 5;

    @Transient
    public boolean isValid() {
        return isValidId(this.username) && isValidPassword(this.password);
    }

    @Transient
    public static boolean isValidId(String userName) {
        return ValidationPattern.EMAIL_Check.matcher(userName).matches();
    }

    @Transient
    public static boolean isValidPassword(String userPassword) {
        int userPasswordLength = userPassword.length();

        return userPasswordLength >= MIN_PASSWORD_LENGTH &&
                userPasswordLength <= MAX_PASSWORD_LENGTH;
    }
}
