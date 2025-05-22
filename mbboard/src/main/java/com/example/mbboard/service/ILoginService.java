package com.example.mbboard.service;

import com.example.mbboard.dto.Member;

public interface ILoginService {
	Member login(Member paramMember);
	void changeMemberPwByAdmin(Member member);
	int updateMemberPw(String memberId, String currentPw, String newPw);
	int rechangeMemberPw(String memberId, String memberPw, String reMemberPw);
	Member selectIdEmail(Member member);
}
