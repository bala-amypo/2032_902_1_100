package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    // REQUIRED constructor
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {

        if (vendor == null) {
            throw new ValidationException("Vendor data is required");
        }

        if (vendor.getVendorName() == null || vendor.getVendorName().isBlank()) {
            throw new ValidationException("Vendor name is required");
        }

        if (vendorRepository.existsByVendorName(vendor.getVendorName())) {
            throw new ValidationException("Vendor name already exists");
        }

        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor getVendor(Long id) {

        if (id == null) {
            throw new ValidationException("Vendor ID is required");
        }

        return vendorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vendor not found"));
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}
