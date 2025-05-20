package com.example.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.schedule.dto.Member;
import com.example.schedule.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MemberService implements IMemberService {
	@Autowired MemberMapper memberMapper;
    
	
	@Override
	public int insertMember(Member member) {
		return memberMapper.insertMember(member);
	}
	@Override
	public String selectId(String id) {
		return memberMapper.selectId(id);
	}
	@Override
	public Member login(Member member) {
		return memberMapper.login(member);
	}
	@Override
	public Member selectIdEmail(Member member) {
		return memberMapper.selectIdEmail(member);
	}
	@Override
	public int insertLoginDate(Member member) {
		return memberMapper.insertLoginDate(member);
	}
	@Override
	public void updateActiveOneYear() {
		
	}
	@Override
	public int updateLoginActive(Member member) {
		return memberMapper.updateLoginActive(member);
	}
	@Override
	public List<Member> selectOff() {
		return memberMapper.selectOff();
	}
}
