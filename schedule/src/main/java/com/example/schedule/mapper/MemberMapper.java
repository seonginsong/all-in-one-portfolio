package com.example.schedule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.schedule.dto.Member;

@Mapper
public interface MemberMapper {
	// 회원가입
	int insertMember(Member member);
	// id 중복검사
	String selectId(String id);
	// 로그인
	Member login(Member member);
	// 이메일 보내기 위한 id, 이메일
	Member selectIdEmail(Member member);
	// 접속기록 update
	int insertLoginDate(Member member);
	// 1년마다 active update
	void updateActiveOneYear();
	// 로그인 시 active update
	int updateLoginActive(Member member);
	// OFF인 id, email
	List<Member> selectOff();
}
