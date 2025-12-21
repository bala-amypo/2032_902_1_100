package com.example.demo.repository;

import com.example.demo.entity.ComplianceRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplianceRuleRepository extends JpaRepository<ComplianceRule, Long> {
}
