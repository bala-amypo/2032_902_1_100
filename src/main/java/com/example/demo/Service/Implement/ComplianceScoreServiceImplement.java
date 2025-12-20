package com.example.demo.service.impl;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ComplianceScoreService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplianceScoreServiceImpl implements ComplianceScoreService {

    private final ComplianceScoreRepository complianceScoreRepository;
    private final VendorRepository vendorRepository;
    private final VendorDocumentRepository vendorDocumentRepository;
    private final ComplianceRuleRepository complianceRuleRepository;

    public ComplianceScoreServiceImpl(ComplianceScoreRepository complianceScoreRepository,
                                      VendorRepository vendorRepository,
                                      VendorDocumentRepository vendorDocumentRepository,
                                      ComplianceRuleRepository complianceRuleRepository) {
        this.complianceScoreRepository = complianceScoreRepository;
        this.vendorRepository = vendorRepository;
        this.vendorDocumentRepository = vendorDocumentRepository;
        this.complianceRuleRepository = complianceRuleRepository;
    }

    @Override
    public ComplianceScore evaluateVendor(Long vendorId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ValidationException("Vendor not found"));

        List<VendorDocument> documents = vendorDocumentRepository.findByVendor_Id(vendorId);

        double score = documents.stream()
                .filter(VendorDocument::getIsValid)
                .mapToDouble(d -> d.getDocumentType().getWeight())
                .sum();

        if (score < 0) {
            throw new ValidationException("Compliance score cannot be negative");
        }

        ComplianceScore complianceScore = complianceScoreRepository
                .findByVendor_Id(vendorId)
                .orElse(new ComplianceScore());

        complianceScore.setVendor(vendor);
        complianceScore.setScoreValue(score);
        complianceScore.setLastEvaluated(LocalDateTime.now());

        if (score >= 80) complianceScore.setRating("EXCELLENT");
        else if (score >= 60) complianceScore.setRating("GOOD");
        else if (score >= 40) complianceScore.setRating("POOR");
        else complianceScore.setRating("NON_COMPLIANT");

        return complianceScoreRepository.save(complianceScore);
    }

    @Override
    public ComplianceScore getScore(Long vendorId) {
        return complianceScoreRepository.findByVendor_Id(vendorId).orElse(null);
    }

    @Override
    public List<ComplianceScore> getAllScores() {
        return complianceScoreRepository.findAll();
    }
}
