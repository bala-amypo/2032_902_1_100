package com.example.demo.service.impl;

import com.example.demo.model.DocumentType;
import com.example.demo.repository.DocumentTypeRepository;
import com.example.demo.service.DocumentTypeService;
import org.springframework.stereotype.Service;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public DocumentType createDocumentType(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }
}