package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "compliance_scores",
    uniqueConstraints = @UniqueConstraint(columnNames = "vendor_id")
)
public class ComplianceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Column(nullable = false)
    private Double scoreValue;

    private LocalDateTime lastEvaluated;

    private String rating;

    public ComplianceScore() {}

    public ComplianceScore(Vendor vendor, Double scoreValue, String rating) {
        this.vendor = vendor;
        this.scoreValue = scoreValue;
        this.rating = rating;
    }

    
}
