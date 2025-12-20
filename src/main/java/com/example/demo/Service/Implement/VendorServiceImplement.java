package com.example.demo.Service.Implement;

import com.example.demo.exception.ValidationException;
import com.example.demo.Entity.Vendor;
import com.example.demo.Repository.VendorRepository;
import com.example.demo.Service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImplement implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImplement(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        if (vendorRepository.existsByVendorName(vendor.getVendorName())) {
            throw new ValidationException("Vendor name already exists");
        }
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor getVendor(Long id) {
        return vendorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}
