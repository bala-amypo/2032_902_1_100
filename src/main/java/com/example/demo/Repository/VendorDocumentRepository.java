package com.example.demo.Repository;

import com.example.demo.Entity.VendorDocument;
import org.springframework.data.jpa.Repository.JpaRepository;

import java.util.List;

public interface VendorDocumentRepository extends JpaRepository<VendorDocument, Long> {

    List<VendorDocument> findByVendor_Id(Long vendorId);
}
