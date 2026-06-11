package com.bloodbank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "donors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Min(18) @Max(65)
    private int age;

    @NotBlank
    private String bloodGroup;

    @NotBlank
    @Column(unique = true)
    private String contact;

    @Email
    @Column(unique = true)
    private String email;

    private String address;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
