package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Vendor vendor;

    @ManyToOne(optional = false)
    private DocumentType documentType;

    @Column(nullable = false)
    private String fileUrl;

    private LocalDateTime uploadedAt;

    private LocalDate expiryDate;

    private Boolean isValid;

    @PrePersist
    void prePersist() {
        uploadedAt = LocalDateTime.now();

        if (expiryDate != null && expiryDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date must be in the future");
        }

        isValid = (expiryDate == null || expiryDate.isAfter(LocalDate.now()));
    }
}
