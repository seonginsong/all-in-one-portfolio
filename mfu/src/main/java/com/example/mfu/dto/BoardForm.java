package com.example.mfu.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardForm {
	private String boardTitle;
	List<MultipartFile> boardfile;
}
