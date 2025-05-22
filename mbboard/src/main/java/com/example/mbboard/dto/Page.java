package com.example.mbboard.dto;

import lombok.Data;

@Data
public class Page {
	
	private int rowPerPage;
	private int currentPage;
	
	private int beginRow;
	private int totalCnt;
	
	private String searchWord;
	
	public Page(int rowPerPage, int currentPage, int totalCnt, String searchWord) {
		this.rowPerPage = rowPerPage;
		this.currentPage = currentPage;
		this.totalCnt = totalCnt;
		this.beginRow = (currentPage - 1) * rowPerPage;
		this.searchWord = (searchWord != null && !searchWord.trim().isEmpty()) ? searchWord : null;
	}
	
	public int getLastPage() {
		int lastPage = this.totalCnt / this.rowPerPage;
		if(this.totalCnt % this.rowPerPage != 0) {
			lastPage += 1;
		}
		return lastPage;
	}
}
