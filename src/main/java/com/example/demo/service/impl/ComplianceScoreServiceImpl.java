package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ComplianceScoreService;
import com.example.demo.util.ComplianceScoringEngine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplianceScoreServiceImpl implements ComplianceScoreService {
    private final VendorRepository vendorRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final VendorDocumentRepository vendorDocumentRepository;
    private final ComplianceScoreRepository complianceScoreRepository;
    private final ComplianceScoringEngine scoringEngine = new ComplianceScoringEngine();

    public ComplianceScoreServiceImpl(VendorRepository vendorRepository,
                                    DocumentTypeRepository documentTypeRepository,
                                    VendorDocumentRepository vendorDocumentRepository,
                                    ComplianceScoreRepository complianceScoreRepository) {
        this.vendorRepository = vendorRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.vendorDocumentRepository = vendorDocumentRepository;
        this.complianceScoreRepository = complianceScoreRepository;
    }

    public ComplianceScore evaluateVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        List<DocumentType> requiredTypes = documentTypeRepository.findByRequiredTrue();
        List<VendorDocument> vendorDocs = vendorDocumentRepository.findByVendor(vendor);

        List<DocumentType> validDocTypes = vendorDocs.stream()
                .filter(doc -> doc.getIsValid() != null && doc.getIsValid())
                .map(VendorDocument::getDocumentType)
                .collect(Collectors.toList());

        double score = scoringEngine.calculateScore(requiredTypes, validDocTypes);
        String rating = scoringEngine.deriveRating(score);

        ComplianceScore complianceScore = new ComplianceScore();
        complianceScore.setVendor(vendor);
        complianceScore.setScoreValue(score);
        complianceScore.setRating(rating);

        return complianceScoreRepository.save(complianceScore);
    }

    public ComplianceScore getScore(Long vendorId) {
        return complianceScoreRepository.findByVendor_Id(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
    }
}