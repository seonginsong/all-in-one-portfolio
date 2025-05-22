package com.example.mbboard.dto;

import lombok.Data;

@Data
public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardUser;
	private String updatedate;
	private String createdate;
}
