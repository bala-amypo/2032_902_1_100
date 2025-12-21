package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "compliance_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplianceRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    private String ruleDescription;

    @Column(nullable = false)
    private String matchType;

    @Column(nullable = false)
    private Double threshold;

    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
        if (threshold == null || threshold < 0) {
            threshold = 0.0;
        }
    }
}
