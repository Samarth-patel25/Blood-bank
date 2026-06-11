package com.bloodbank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "blood_inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String bloodGroup;

    @Min(0)
    private int availableUnits;

    private int reservedUnits;

    public int getTotalUnits() {
        return availableUnits + reservedUnits;
    }
}
