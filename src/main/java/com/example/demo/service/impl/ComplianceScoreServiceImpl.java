package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ComplianceScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplianceScoreServiceImpl implements ComplianceScoreService {

    private final VendorRepository vendorRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final VendorDocumentRepository vendorDocumentRepository;
    private final ComplianceScoreRepository scoreRepository;

    @Override
    public ComplianceScore evaluateVendor(Long vendorId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vendor not found with id: " + vendorId));

        List<DocumentType> requiredTypes = documentTypeRepository.findByRequiredTrue();
        List<VendorDocument> documents = vendorDocumentRepository.findByVendor(vendor);

        int totalWeight = requiredTypes.stream()
                .mapToInt(DocumentType::getWeight)
                .sum();

        int achievedWeight = 0;

        for (DocumentType type : requiredTypes) {
            boolean valid = documents.stream()
                    .anyMatch(doc ->
                            doc.getDocumentType().equals(type) &&
                            (doc.getExpiryDate() == null ||
                             doc.getExpiryDate().isAfter(LocalDate.now())));
            if (valid) {
                achievedWeight += type.getWeight();
            }
        }

        double score = totalWeight == 0 ? 100 :
                ((double) achievedWeight / totalWeight) * 100;

        if (score < 0) {
            throw new ValidationException("Compliance score cannot be negative");
        }

        ComplianceScore complianceScore = scoreRepository
                .findByVendorId(vendorId)
                .orElse(new ComplianceScore());

        complianceScore.setVendor(vendor);
        complianceScore.setScoreValue(score);
        complianceScore.setLastEvaluated(LocalDateTime.now());
        complianceScore.setRating(getRating(score));

        return scoreRepository.save(complianceScore);
    }

    @Override
    public ComplianceScore getScore(Long vendorId) {
        return scoreRepository.findByVendorId(vendorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Compliance score not found for vendor id: " + vendorId));
    }

    @Override
    public List<ComplianceScore> getAllScores() {
        return scoreRepository.findAll();
    }

    private String getRating(double score) {
        if (score >= 85) return "EXCELLENT";
        if (score >= 70) return "GOOD";
        if (score >= 50) return "POOR";
        return "NONCOMPLIANT";
    }
}
