package com.example.demo.controller;

import com.example.demo.model.ComplianceScore;
import com.example.demo.service.ComplianceScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compliance-scores")
public class ComplianceScoreController {

    @Autowired
    private ComplianceScoreService complianceScoreService;

    @PostMapping("/evaluate/{vendorId}")
    public ResponseEntity<ComplianceScore> evaluateVendor(@PathVariable Long vendorId) {
        ComplianceScore score = complianceScoreService.evaluateVendor(vendorId);
        return ResponseEntity.ok(score);
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<ComplianceScore> getScore(@PathVariable Long vendorId) {
        ComplianceScore score = complianceScoreService.getScore(vendorId);
        return ResponseEntity.ok(score);
    }
}