<%@page import="dto.Cash"%>
<%@page import="model.CashDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 공백으로 제출시 오류 방지
	if(request.getParameter("cashDate") == null || request.getParameter("memo") == null || request.getParameter("amount") == null || request.getParameter("category_no") == null ||
	request.getParameter("cashDate").equals("") || request.getParameter("memo").equals("") || request.getParameter("amount").equals("") || request.getParameter("category_no").equals("")) {
		response.sendRedirect("/cashbook/cash/insertCashFormDirect.jsp?msg=NoInsert");
		return;
	}

	String cashDate = request.getParameter("cashDate");
	String memo = request.getParameter("memo");
	String color = request.getParameter("color");
	int amount = Integer.parseInt(request.getParameter("amount"));
 	int categoryNo = Integer.parseInt(request.getParameter("category_no"));
	System.out.println("cashDate:"+cashDate);
	System.out.println("memo:"+memo);
	System.out.println("color:"+color);
	System.out.println("amount:"+amount);

	CashDao cashDao = new CashDao();
	Cash c = new Cash();
	c.setCashDate(cashDate);
	c.setAmount(amount);
	c.setColor(color);
	c.setCategoryNo(categoryNo);
	c.setMemo(memo);
	
	String arr[] = cashDate.split("-");
	
	System.out.println(arr[0]+arr[1]+arr[2]);
	int y = Integer.parseInt(arr[0]);
	int m = Integer.parseInt(arr[1]);
	int d = Integer.parseInt(arr[2]);
	
	cashDao.insertCash(c);
	response.sendRedirect("/cashbook/dateList.jsp?y="+y+"&m="+m+"&d="+d);
	
%>