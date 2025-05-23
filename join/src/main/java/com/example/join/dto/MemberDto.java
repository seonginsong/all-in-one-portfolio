package com.example.join.dto;

import java.util.List;

import lombok.Data;

@Data
public class MemberDto {
	private String id;
	private String pw;
	private String name;
	private String birth;
	private int age;
	private String gender;
	private List<String> hobby;
	private String emailId;
	private String emailAddr;
	private String memo;
}
