package com.example.ajax.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	// id 중복검사
	String selectMemberId(String id);
	// 회원가입
	int insertMember(Map<String, Object> member);
}
