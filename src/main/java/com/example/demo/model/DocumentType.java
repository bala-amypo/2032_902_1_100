package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "document_types",
    uniqueConstraints = @UniqueConstraint(columnNames = "typeName")
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String typeName;

    private String description;

    private Boolean required;

    @Column(nullable = false)
    private Integer weight;

    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "supportedDocumentTypes")
    @Builder.Default
    private Set<Vendor> vendors = new HashSet<>();

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
        if (weight == null || weight < 0) {
            weight = 0;
        }
    }
}
