package com.example.demo.repository;

import com.example.demo.model.Vendor;
import com.example.demo.model.VendorDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendorDocumentRepository extends JpaRepository<VendorDocument, Long> {
    @Query("SELECT vd FROM VendorDocument vd WHERE vd.expiryDate < :date")
    List<VendorDocument> findExpiredDocuments(@Param("date") LocalDate date);
    
    List<VendorDocument> findByVendor(Vendor vendor);
}