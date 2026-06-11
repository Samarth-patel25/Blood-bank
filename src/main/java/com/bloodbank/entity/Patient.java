package com.bloodbank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private int age;

    @NotBlank
    private String bloodGroup;

    private String disease;

    @NotBlank
    private String contact;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
