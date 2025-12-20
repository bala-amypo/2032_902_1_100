package com.example.demo.Service;

import com.example.demo.Entity.Vendor;

import java.util.List;

public interface VendorService {

    Vendor createVendor(Vendor vendor);

    Vendor getVendor(Long id);

    List<Vendor> getAllVendors();
}
