package com.example.demo.Service;

import com.example.demo.Entity.DocumentType;

import java.util.List;

public interface DocumentTypeService {

    DocumentType createDocumentType(DocumentType type);

    List<DocumentType> getAllDocumentTypes();

    DocumentType getDocumentType(Long id);
}
