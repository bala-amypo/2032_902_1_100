package com.example.demo.repository;

import com.example.demo.model.ComplianceRule;

public interface ComplianceRuleRepository {
    ComplianceRule save(ComplianceRule complianceRule);
}