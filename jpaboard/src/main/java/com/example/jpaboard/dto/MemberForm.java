package com.example.jpaboard.dto;

import com.example.jpaboard.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {
	private Integer memberNo;
	private String memberId;
	private String memberPw;
	private String memberRole;
	
	public Member toEntity() {
		Member entity = new Member();
		entity.setMemberNo(this.memberNo);
		entity.setMemberId(this.memberId);
		entity.setMemberPw(this.memberPw);
		entity.setMemberRole(this.memberRole);
		return entity;
	}
}
