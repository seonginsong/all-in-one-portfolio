package com.example.schedule.service;

import java.util.List;

import com.example.schedule.dto.Member;

public interface IMemberService {
	int insertMember(Member member);
	Member login(Member member);
	Member selectIdEmail(Member member);
	String selectId(String id);
	int insertLoginDate(Member member);
	void updateActiveOneYear();
	int updateLoginActive(Member member);
	List<Member> selectOff();
}
