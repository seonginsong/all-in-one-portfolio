package com.example.ajax.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajax.mapper.MemberMapper;

@RestController
public class MemberRest {
	@Autowired MemberMapper memberMapper;
	
	@GetMapping("/isId/{id}")
	public boolean isId(@PathVariable String id) {
		boolean result;
		if(memberMapper.selectMemberId(id) != null) {
			return true;
		}
		return false;
	}
}
