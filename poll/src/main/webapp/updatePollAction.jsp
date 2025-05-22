<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.*"%>
<%@page import="dto.*"%>
<%
	// question 관련 데이터값 가져오기
	Integer qnum = Integer.parseInt(request.getParameter("qnum"));
	String title = request.getParameter("title");
	String startdate = request.getParameter("startdate");
	String enddate = request.getParameter("enddate");
	int type = Integer.parseInt(request.getParameter("type"));
	
	// item 관련 데이터값 가져오기
	String[] content = request.getParameterValues("content");
	// 공백 요소를 제거 후 새로운 배열(ArrayList<String>)에 저장
	ArrayList<String> contentList = new ArrayList<>();
	for(String c : content) {
		if(!c.equals("")) {
			contentList.add(c);
		}
	}
	// Item 배열 만들기
	ArrayList<Item> itemList = new ArrayList<>();
	int i = 1;
	for(String c : contentList) {
		Item item = new Item();
		item.setQnum(qnum);
		item.setInum(i);
		item.setContent(c);
		itemList.add(item);
		i++;
	}
	
	// 순서 : update question -> delete item -> insert item
	
	// 1) update question
	Question q = new Question();
	q.setTitle(title);
	q.setStartdate(startdate);
	q.setEnddate(enddate);
	q.setType(type);
	q.setNum(qnum);
	
	// QuestionDao 호출
	QuestionDao questionDao = new QuestionDao();
	questionDao.updateQuestion(q);
	
	// 2) ItemDao(delete) 호출
	ItemDao itemDao = new ItemDao();
	itemDao.deleteItem(qnum);
	
	// 3) ItemDao(insert)
	for(Item item : itemList) {
		itemDao.insertItem(item);
	}
	
	// 수정을 했으면 바로 확인 위해 item이 보이는 페이지로..but 투표하기 페이지에 있으므로 pollList로
	response.sendRedirect("/poll/pollList.jsp");
%>