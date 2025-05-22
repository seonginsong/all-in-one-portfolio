package com.example.mbboard.dto;

import lombok.Data;

@Data
public class Member {
	private String memberId;
	private String memberPw;
	private String memberRole;
	private String saveIdCk;
	private String pwcktime;
	private String email;
}
