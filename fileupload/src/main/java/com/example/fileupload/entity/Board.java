package com.example.fileupload.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "board")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bno;
	@Column(name = "title")
	private String title;
	@Column(name = "pw")
	private String pw;
	
	// 쓴다면 여기 연관관계를 쓰는게 맞음 - 게시물에 들어가면 그 게시물 관련 파일들 나열
	//@OneToMany(mappedBy = "board")
	//private List<Boardfile> boardfiles = new ArrayList<>();
}
