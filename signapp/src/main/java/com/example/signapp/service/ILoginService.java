package com.example.signapp.service;




import com.example.signapp.dto.Employee;



public interface ILoginService {
	Employee login(Employee member); 	//로그인
	void joinEmployee(Employee employee);
	
}
