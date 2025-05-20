package com.example.schedule.schedules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.schedule.dto.Member;
import com.example.schedule.service.IMemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MySchedule {
	@Autowired JavaMailSender javaMailSender;
	@Autowired IMemberService memberService;
	
	@Scheduled(cron = "0 * * * * *")// cron= "0 59 23 25 * *" -> 요구사항
	public void myScheduleTest() {
		log.info("스케쥴러 테스트");
		// 접속기간이 1년이 지났을 경우 active = OFF
		memberService.updateActiveOneYear();
		List<Member> list = memberService.selectOff();
		for(Member member : list) {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("admin@localhost.com");
			msg.setTo(member.getEmail());
			msg.setSubject(member.getId() + "님은 휴면계정입니다");
			msg.setText("로그인하세요");
			javaMailSender.send(msg);
		}
	}
	/*
	 * 휴면 계정으로 만드는 서비스 메서드
	 * 메일을 보내는 서비스 메서드
	 */
}
