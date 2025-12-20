package com.example.demo.Service.Implement;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.Entity.DocumentType;
import com.example.demo.Entity.Vendor;
import com.example.demo.Entity.VendorDocument;
import com.example.demo.Repository.DocumentTypeRepository;
import com.example.demo.Repository.VendorDocumentRepository;
import com.example.demo.Repository.VendorRepository;
import com.example.demo.Service.VendorDocumentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendorDocumentServiceImplement implements VendorDocumentService {

    private final VendorDocumentRepository vendorDocumentRepository;
    private final VendorRepository vendorRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public VendorDocumentServiceImplement(VendorDocumentRepository vendorDocumentRepository,
                                     VendorRepository vendorRepository,
                                     DocumentTypeRepository documentTypeRepository) {
        this.vendorDocumentRepository = vendorDocumentRepository;
        this.vendorRepository = vendorRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public VendorDocument uploadDocument(Long vendorId, Long typeId, VendorDocument document) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        DocumentType type = documentTypeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("Document Type not found"));

        if (document.getExpiryDate() != null &&
                document.getExpiryDate().isBefore(LocalDate.now())) {
            throw new ValidationException("Document has expired");
        }

        document.setVendor(vendor);
        document.setDocumentType(type);
        document.setIsValid(true);

        return vendorDocumentRepository.save(document);
    }

    @Override
    public List<VendorDocument> getDocumentsForVendor(Long vendorId) {
        return vendorDocumentRepository.findByVendor_Id(vendorId);
    }

    @Override
    public VendorDocument getDocument(Long id) {
        return vendorDocumentRepository.findById(id).orElse(null);
    }
}
