package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class VendorDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private DocumentType documentType;

    private String fileUrl;

    private LocalDateTime uploadedAt;

    private LocalDate expiryDate;

    private Boolean isValid;

    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
        if (expiryDate == null || expiryDate.isAfter(LocalDate.now())) {
            this.isValid = true;
        } else {
            this.isValid = false;
        }
    }
}
