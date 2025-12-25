package com.example.demo.repository;

import com.example.demo.model.Vendor;
import java.util.Optional;

public interface VendorRepository {
    Vendor save(Vendor vendor);
    Optional<Vendor> findById(Long id);
}