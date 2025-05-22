package com.example.mbboard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mbboard.dto.Member;
import com.example.mbboard.mapper.MemberMapper;

@RestController
public class MemberRest {
	@Autowired MemberMapper memberMapper;
	
	@GetMapping("/isId/{memberId}")
	public boolean isId(@PathVariable String memberId) {
		boolean result;
		if(memberMapper.selectMemberId(memberId) != null) {
			return true;
		}
		return false;
	}
	
	@PostMapping("/updateRole/{memberId}")
	public int updateRole(@PathVariable String memberId) {
		int row = memberMapper.updateMemberRole(memberId);
		return row;
	}

}
