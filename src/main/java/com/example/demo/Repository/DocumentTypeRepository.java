package com.example.demo.Repository;

import com.example.demo.Entity.DocumentType;
import org.springframework.data.jpa.Repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

    boolean existsByTypeName(String typeName);
}
