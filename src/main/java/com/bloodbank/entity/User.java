package com.bloodbank.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data //lombook annotations
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // BCrypt encoded

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        ADMIN, DONOR, HOSPITAL, STAFF
    }
}
