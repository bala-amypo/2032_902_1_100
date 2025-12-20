package com.example.demo.Repository;

import com.example.demo.Entity.Vendor;
import org.springframework.data.jpa.Repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    boolean existsByVendorName(String vendorName);
}
