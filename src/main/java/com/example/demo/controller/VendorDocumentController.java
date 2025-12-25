package com.example.demo.controller;

import com.example.demo.model.VendorDocument;
import com.example.demo.service.VendorDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor-documents")
public class VendorDocumentController {

    @Autowired
    private VendorDocumentService vendorDocumentService;

    @PostMapping("/upload")
    public ResponseEntity<VendorDocument> uploadDocument(
            @RequestParam Long vendorId,
            @RequestParam Long documentTypeId,
            @RequestBody VendorDocument document) {
        VendorDocument savedDocument = vendorDocumentService.uploadDocument(vendorId, documentTypeId, document);
        return ResponseEntity.ok(savedDocument);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDocument> getDocument(@PathVariable Long id) {
        VendorDocument document = vendorDocumentService.getDocument(id);
        return ResponseEntity.ok(document);
    }
}