package com.example.signapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.signapp.dto.Document;
import com.example.signapp.mapper.DocumentMapper;

@Service
public class IDocumentService implements DocumentService {

    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public List<Document> getDocumentList() {
        return documentMapper.selectDocumentList();
    }

    @Override
    public void writeDocument(Document document) {
        documentMapper.writeDocument(document);
    }
}
