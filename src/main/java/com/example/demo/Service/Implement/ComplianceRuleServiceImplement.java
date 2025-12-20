package com.example.demo.Service.;

import com.example.demo.Entity.ComplianceRule;
import com.example.demo.Repository.ComplianceRuleRepository;
import com.example.demo.Service.ComplianceRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceRuleServiceImpl implements ComplianceRuleService {

    private final ComplianceRuleRepository complianceRuleRepository;

    public ComplianceRuleServiceImpl(ComplianceRuleRepository complianceRuleRepository) {
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
