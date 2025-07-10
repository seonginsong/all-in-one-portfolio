package com.example.oauth2client.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.oauth2client.dto.CustomOAuth2User;

@Service
public class CustomOAuth2Service extends DefaultOAuth2UserService{
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// 인증서버 아이디값 디버깅
		System.out.println(userRequest.getClientRegistration().getRegistrationId());
		
		/*
		if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			
		} else if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			
		} else {
			System.out.println("인증서브 오류");
		}
		*/
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		// attributes 속성 디버깅
		System.out.println(oAuth2User.getAttributes());
		
		CustomOAuth2User customOAuth2User = new CustomOAuth2User(oAuth2User.getAttributes(),"ROLE_NAVER");
		return customOAuth2User;
	}
}
