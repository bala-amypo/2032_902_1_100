package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_scores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplianceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "vendor_id", unique = true)
    private Vendor vendor;

    @Column(nullable = false)
    private Double scoreValue;

    private LocalDateTime lastEvaluated;

    private String rating;

    @PrePersist
    void prePersist() {
        if (scoreValue < 0 || scoreValue > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        lastEvaluated = LocalDateTime.now();
    }
}
