package com.example.mfu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MfuApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(MfuApplication.class, args);
	}

	// 파일 저장 경로 맵핑(물리적 경로 : 프로젝트 밖, 논리적 접근 : 프로젝트 안인 것처럼 접근)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 서비스하면 addResourceHandler(프로젝트 안에서 사용하는 경로).addResourceLocations(서비스 PC폴더 위치)
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///c:/project/upload");
	}
}
