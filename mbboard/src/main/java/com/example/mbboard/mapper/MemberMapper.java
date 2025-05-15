package com.example.mbboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mbboard.dto.Member;

@Mapper
public interface MemberMapper {
	// id 중복검사
	String selectMemberId(String memberId);
	// 회원가입
	int insertMember(Member member);
	// 멤버리스트
	List<Member> selectMemberList();
	// Role변경
	int updateMemberRole(String memberId);
}
