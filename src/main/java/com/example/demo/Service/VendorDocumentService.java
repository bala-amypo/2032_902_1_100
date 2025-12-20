package com.example.demo.Service;

import com.example.demo.Entity.VendorDocument;

import java.util.List;

public interface VendorDocumentService {

    VendorDocument uploadDocument(Long vendorId, Long typeId, VendorDocument document);

    List<VendorDocument> getDocumentsForVendor(Long vendorId);

    VendorDocument getDocument(Long id);
}
