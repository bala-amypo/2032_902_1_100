package com.example.demo.repository;

import com.example.demo.model.Vendor;
import com.example.demo.model.VendorDocument;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VendorDocumentRepository {
    VendorDocument save(VendorDocument vendorDocument);
    Optional<VendorDocument> findById(Long id);
    List<VendorDocument> findExpiredDocuments(LocalDate date);
    List<VendorDocument> findByVendor(Vendor vendor);
}