package com.example.demo.Service;

import com.example.demo.Entity.ComplianceScore;

import java.util.List;

public interface ComplianceScoreService {

    ComplianceScore evaluateVendor(Long vendorId);

    ComplianceScore getScore(Long vendorId);

    List<ComplianceScore> getAllScores();
}
