package com.example.mbboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mbboard.dto.Member;
import com.example.mbboard.mapper.LoginMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class LoginService implements ILoginService {
	@Autowired LoginMapper loginMapper;
	
	@Override
	public Member login(Member paramMember) {
		return loginMapper.login(paramMember);
	}
}
