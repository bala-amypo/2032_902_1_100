package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.ComplianceRule;
import com.example.demo.repository.ComplianceRuleRepository;
import com.example.demo.service.ComplianceRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplianceRuleServiceImpl implements ComplianceRuleService {

    private final ComplianceRuleRepository ruleRepository;

    @Override
    public ComplianceRule createRule(ComplianceRule rule) {
        if (rule.getRuleName() != null &&
            ruleRepository.findAll().stream()
                .anyMatch(r -> r.getRuleName().equals(rule.getRuleName()))) {
            throw new ValidationException("Rule name already exists");
        }
        return ruleRepository.save(rule);
    }

    @Override
    public List<ComplianceRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public ComplianceRule getRule(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Compliance rule not found with id: " + id));
    }
}
