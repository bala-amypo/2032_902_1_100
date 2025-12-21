package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_documents")
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

    public VendorDocument() {}

    public VendorDocument(Vendor vendor, DocumentType documentType,
                          String fileUrl, LocalDate expiryDate) {
        this.vendor = vendor;
        this.documentType = documentType;
        this.fileUrl = fileUrl;
        this.expiryDate = expiryDate;
    }

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
