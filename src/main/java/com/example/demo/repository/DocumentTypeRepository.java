package com.example.demo.repository;

import com.example.demo.model.DocumentType;
import java.util.List;
import java.util.Optional;

public interface DocumentTypeRepository {
    DocumentType save(DocumentType documentType);
    Optional<DocumentType> findById(Long id);
    List<DocumentType> findByRequiredTrue();
}