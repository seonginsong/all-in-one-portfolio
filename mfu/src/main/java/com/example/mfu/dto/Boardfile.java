package com.example.mfu.dto;

import lombok.Data;

@Data
public class Boardfile {
	private int boardfileNo;
	private int boardNo;
	private String filename;
	private String filetype;
	private String boardTitle;
}
