package com.example.mbboard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mbboard.dto.Member;

@Mapper
public interface LoginMapper {
	Member login(Member paramMember);

	int updateMemberPwByAdmin(Member member);
	
	int updateMemberPw(@Param("memberId") String memberId,
            @Param("currentPw") String currentPw,
            @Param("newPw") String newPw);
	
	int rechangeMemberPw(@Param("memberId") String memberId,
			@Param("memberPw") String memberPw,
			@Param("reMemberPw") String reMemberPw);
	
	Member selectIdEmail(Member member);
}
