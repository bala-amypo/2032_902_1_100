package com.example.demo.controller;

import com.example.demo.model.ComplianceRule;
import com.example.demo.service.ComplianceRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compliance-rules")
public class ComplianceRuleController {

    @Autowired
    private ComplianceRuleService complianceRuleService;

    @PostMapping
    public ResponseEntity<ComplianceRule> createRule(@RequestBody ComplianceRule rule) {
        ComplianceRule savedRule = complianceRuleService.createRule(rule);
        return ResponseEntity.ok(savedRule);
    }
}