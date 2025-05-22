package com.example.mbboard.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mbboard.dto.Member;
import com.example.mbboard.mapper.LoginMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class LoginService implements ILoginService {
	@Autowired JavaMailSender javaMailSender;
	
	@Autowired LoginMapper loginMapper;
	
	@Override
	public Member login(Member paramMember) {
		return loginMapper.login(paramMember);
	}
	
	@Override
	public void changeMemberPwByAdmin(Member member) {
		// 새로운 패스워드를 생성
		String randomPw = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
		member.setMemberPw(randomPw);
		int row = loginMapper.updateMemberPwByAdmin(member);
		if(row == 1) {
			//메일로 변경된 비밀번호 전송
			log.info("변경된 비밀번호 : " + randomPw);
			
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("admin@localhost.com");
			msg.setTo(member.getEmail());
			msg.setSubject("변경된 비밀번호");
			msg.setText(randomPw);
			javaMailSender.send(msg);
		}
	}

	@Override
	public int updateMemberPw(String memberId, String currentPw, String newPw) {
		int row = loginMapper.updateMemberPw(memberId, currentPw, newPw);
		if (row > 0) {
		    System.out.println("비밀번호 변경 성공");
		} else {
		    System.out.println("비밀번호 변경 실패 (비밀번호 불일치)");
		}
		return row;
	}

	@Override
	public int rechangeMemberPw(String memberId, String memberPw, String reMemberPw) {
		int row = loginMapper.rechangeMemberPw(memberId, memberPw, reMemberPw);
		if (row > 0) {
		    System.out.println("비밀번호 변경 성공");
		} else {
		    System.out.println("비밀번호 변경 실패 (비밀번호 불일치)");
		}
		return row;
	}

	@Override
	public Member selectIdEmail(Member member) {
		return loginMapper.selectIdEmail(member);
	}
}
