package com.example.demo.service.impl;

import com.example.demo.model.ComplianceRule;
import com.example.demo.repository.ComplianceRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.ComplianceRuleService;
import org.springframework.stereotype.Service;

@Service
public class ComplianceRuleServiceImpl implements ComplianceRuleService {
    @Autowired
    ComplianceRuleRepository complianceRuleRepository;

    public ComplianceRuleServiceImpl(ComplianceRuleRepository complianceRuleRepository) {
        this.complianceRuleRepository = complianceRuleRepository;
    }

    public ComplianceRule createRule(ComplianceRule rule) {
        return complianceRuleRepository.save(rule);
    }
}