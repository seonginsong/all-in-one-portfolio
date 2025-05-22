package com.example.fileupload.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardForm {
	private String title;
	private String pw;
	private List<MultipartFile> fileList; // spring에서 input type="file" 받을때 사용
}
