package com.example.demo.Service;

import com.example.demo.Entity.ComplianceRule;

import java.util.List;

public interface ComplianceRuleService {

    ComplianceRule createRule(ComplianceRule rule);

    List<ComplianceRule> getAllRules();

    ComplianceRule getRule(Long id);
}
