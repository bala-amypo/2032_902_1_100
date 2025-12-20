package com.example.demo.Repository;

import com.example.demo.Entity.ComplianceRule;
import org.springframework.data.jpa.Repository.JpaRepository;

public interface ComplianceRuleRepository extends JpaRepository<ComplianceRule, Long> {
}
