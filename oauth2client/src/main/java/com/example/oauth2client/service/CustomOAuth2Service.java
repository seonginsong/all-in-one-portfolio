package com.example.oauth2client.service;

import java.util.Map;

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
		
		String provider = userRequest.getClientRegistration().getRegistrationId();
        String role = "ROLE_USER";
        if (provider.equals("naver")) {
            role = "ROLE_NAVER";
        } else if (provider.equals("kakao")) {
            role = "ROLE_KAKAO";
        }
        // attributes에 provider 정보 추가
        Map<String, Object> attributes = new java.util.HashMap<>(oAuth2User.getAttributes());
        attributes.put("provider", provider);
		
		CustomOAuth2User customOAuth2User = new CustomOAuth2User(oAuth2User.getAttributes(),role);
		return customOAuth2User;
	}
}
