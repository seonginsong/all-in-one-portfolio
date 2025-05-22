package com.example.mbboard.service;

import java.util.List;

import com.example.mbboard.dto.Member;

public interface IMemberService {
	int insertMember(Member paramMember);
	List<Member> selectMemberList();
	int updateMemberRole(String memberId);
}
