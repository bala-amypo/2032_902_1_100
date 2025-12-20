package com.example.demo.Repository;

import com.example.demo.Entity.ComplianceScore;
import org.springframework.data.jpa.Repository.JpaRepository;

import java.util.Optional;

public interface ComplianceScoreRepository extends JpaRepository<ComplianceScore, Long> {

    Optional<ComplianceScore> findByVendor_Id(Long vendorId);
}
