package com.example.demo.repository;

import com.example.demo.model.ComplianceScore;
import java.util.Optional;

public interface ComplianceScoreRepository {
    ComplianceScore save(ComplianceScore complianceScore);
    Optional<ComplianceScore> findByVendor_Id(Long vendorId);
}