package com.bloodbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blood_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String bloodGroup;

    @Min(1)
    private int unitsRequired;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime requestDate;

    private String remarks;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public enum RequestStatus {
        PENDING, APPROVED, REJECTED, FULFILLED
    }

    @PrePersist
    public void prePersist() {
        this.requestDate = LocalDateTime.now();
        if (this.status == null) this.status = RequestStatus.PENDING;
    }
}
