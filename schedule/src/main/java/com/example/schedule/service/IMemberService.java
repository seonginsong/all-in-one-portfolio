package com.example.schedule.service;

import java.util.List;

import com.example.schedule.dto.Member;
import com.example.schedule.dto.PwHistory;

public interface IMemberService {
	int insertMember(Member member);
	Member login(Member member);
	Member selectIdEmail(Member member);
	String selectId(String id);
	int insertLoginDate(Member member);
	int updateActiveOneYear();
	int updateLoginActive(Member member);
	List<Member> selectOff();
	
	// pwhistory
	int insertPwHistory(PwHistory ph);
	int updatePw(Member member);
	String checkPw(PwHistory ph);
	void deletePwHistory();
}
