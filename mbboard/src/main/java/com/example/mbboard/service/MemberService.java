package com.example.mbboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.mbboard.MbboardApplication;
import com.example.mbboard.dto.Member;
import com.example.mbboard.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MemberService implements IMemberService{

    private final MbboardApplication mbboardApplication;
	@Autowired MemberMapper memberMapper;

    MemberService(MbboardApplication mbboardApplication) {
        this.mbboardApplication = mbboardApplication;
    }
	
	@Override
	public int insertMember(Member paramMember) {
		return memberMapper.insertMember(paramMember);
	}
	
	@Override
	public List<Member> selectMemberList() {
		return memberMapper.selectMemberList();
	}
	
	@Override
	public int updateMemberRole(String memberId) {
		return memberMapper.updateMemberRole(memberId);
	}
}
