package com.example.demo.Service.Implement;

import com.example.demo.Entity.ComplianceRule;
import com.example.demo.Repository.ComplianceRuleRepository;
import com.example.demo.Service.ComplianceRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceRuleServiceImplement implements ComplianceRuleService {

    private final ComplianceRuleRepository complianceRuleRepository;

    public ComplianceRuleServiceImplement(ComplianceRuleRepository complianceRuleRepository) {
        this.complianceRuleRepository = complianceRuleRepository;
    }

    @Override
    public ComplianceRule createRule(ComplianceRule rule) {
        return complianceRuleRepository.save(rule);
    }

    @Override
    public List<ComplianceRule> getAllRules() {
        return complianceRuleRepository.findAll();
    }

    @Override
    public ComplianceRule getRule(Long id) {
        return complianceRuleRepository.findById(id).orElse(null);
    }
}
