package com.example.signapp.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.signapp.dto.Employee;
import com.example.signapp.mapper.LoginMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class LoginService implements ILoginService {


	@Autowired
	private LoginMapper loginMapper;

	//로그인
	@Override
	public Employee login(Employee employee) {
		return loginMapper.login(employee);
	}


	@Override
	public void joinEmployee(Employee employee) {
		loginMapper.joinEmployee(employee);
	}
	
	

	
}


