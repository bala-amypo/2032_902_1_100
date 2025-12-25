package com.example.demo.util;

import com.example.demo.model.DocumentType;
import java.util.List;

public class ComplianceScoringEngine {

    public double calculateScore(List<DocumentType> requiredTypes, List<DocumentType> validDocTypes) {
        if (requiredTypes.isEmpty()) {
            return 100.0;
        }

        int totalWeight = requiredTypes.stream().mapToInt(DocumentType::getWeight).sum();
        int validWeight = validDocTypes.stream()
                .filter(requiredTypes::contains)
                .mapToInt(DocumentType::getWeight)
                .sum();

        return (double) validWeight / totalWeight * 100.0;
    }

    public String deriveRating(double score) {
        if (score >= 90) return "EXCELLENT";
        if (score >= 70) return "GOOD";
        if (score >= 50) return "POOR";
        return "NON_COMPLIANT";
    }
}