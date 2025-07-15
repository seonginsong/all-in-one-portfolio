package com.example.supa.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardDto {
	private Integer id;
	private String title;
	private String content;
	private Date createAt;
}
