package com.example.signapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.signapp.dto.Document;
@Mapper
public interface DocumentMapper {
	void writeDocument(Document document);
	List<Document> selectDocumentList();
}
