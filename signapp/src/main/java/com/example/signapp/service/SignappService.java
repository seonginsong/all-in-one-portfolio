package com.example.signapp.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.signapp.dto.SignappForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class SignappService {
	public boolean addSign(SignappForm signappForm) {
		// 1) singImg 파일이름을 생성
		String ext = ".png"; // data:image/png;Base64,....
		String filename = UUID.randomUUID().toString().replace("-", "")+ext;
		
		// 2) mapper 호출
		
		
		// 3) 이미지를 디코딩해서 원하는 위치에 저장
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("c:\\sign_img\\"+filename); // throws FileNotFoundException
			// 파일을 만들 수 있는 비어있는 outputstream에 signImg 안의 이미지문자를 디코딩
			String signImg1 = signappForm.getSignImg().split(",")[1];
			fos.write(Base64.getDecoder().decode(signImg1)); // base64, 뒤부터가 이미지문자 // throws IOException 
		} catch (FileNotFoundException e1) { // Exception 으로 전체 예외로 잡음
			log.error("파일생성실패 롤백");
			throw new RuntimeException(); // class SignException extends RuntimeException
		} catch (IOException e2) { // Exception 으로 전체 예외로 잡음
			log.error("파일디코딩실패 롤백");
			throw new RuntimeException(); // class SignException extends RuntimeException
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
