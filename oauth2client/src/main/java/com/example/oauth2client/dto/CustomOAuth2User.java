package com.example.oauth2client.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User{ // OAuth2UserService 반환할 DTO
	
	private Map<String, Object> attributes; // service에서 attributes 값을 주입(setter / 생성자)
	private String role;
	public CustomOAuth2User(Map<String, Object> attributes, String role) {
		this.attributes = attributes;
		this.role = role;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> roleList = new ArrayList<>();
		roleList.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return CustomOAuth2User.this.role;
			}
		});
		return roleList;
	}

	@Override
	public String getName() { // 유니크한 값을 반환
		// 카카오
	    if(this.attributes.containsKey("kakao_account")) {
	        Map<String, Object> kakaoAccount = (Map<String, Object>) this.attributes.get("kakao_account");
	        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
	        return (String) profile.get("nickname");
	    }
	    // 네이버
	    if(this.attributes.containsKey("response")) {
	        Map<String, Object> response = (Map<String, Object>) this.attributes.get("response");
	        return (String) response.get("name");
	    }
	    // 기본
	    return this.attributes.get("id").toString();
	}

}
