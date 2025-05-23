package com.example.signapp.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.signapp.dto.SignForm;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Transactional
@Service
public class SignService {

	public boolean addSign(SignForm signForm) {
		// 0) signImg 파일이름을 생성
		String ext = ".png"; // data:image/png;Base64,xxxxxx....
		String filename = UUID.randomUUID().toString().replace("-", "")+ext;
		
		// 1) mapper 호출
		
		// 2) 이미지를 디코딩해서 원하는 위치에 저장
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("c:\\sign_img\\"+filename); // throws FileNotFoundException
			// 파일을 만들 수 있는 비어있는 OutputStream에 signImg안에 이미지문자(signImg ,뒤부터)를 디코딩
			String signImg1 = signForm.getSignImg().split(",")[1];
			fos.write(Base64.getDecoder().decode(signImg1)); // throws IOException
		} catch (FileNotFoundException e1) {
			log.error("파일생성 실패 @Transactional 록백");
			throw new RuntimeException(); // class SingException extends RuntimeException
		} catch (IOException e2) {
			log.error("파일디코딩 실패 @Transactional 록백");
			throw new RuntimeException(); // class SingException extends RuntimeException
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
