package com.example.signapp.service;

import java.util.List;

import com.example.signapp.dto.Document;

public interface DocumentService {
    void writeDocument(Document document);
    List<Document> getDocumentList();
}
