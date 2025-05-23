package com.example.signapp.mapper;



import org.apache.ibatis.annotations.Mapper;


import com.example.signapp.dto.Employee;


@Mapper
public interface LoginMapper {
	Employee login(Employee employee); //로그인
	void joinEmployee(Employee Employee); //회원가입
	

}
