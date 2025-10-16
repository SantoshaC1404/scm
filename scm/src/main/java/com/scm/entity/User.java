package com.scm.entity;

import com.scm.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(length = 100)
    private String about;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;
}
