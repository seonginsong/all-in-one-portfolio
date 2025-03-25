<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Rental List</h1>
	<form action="/sakila/d0325/rentalList.jsp">
		Store : 
		<select name="storeId">
			<option value="0">전체</option>
			<option value="1">1지점</option>
			<option value="2">2지점</option>
		</select>
		<button type="submit">검색</button>
	</form>
	
	
	<table border="1">
		<tr>
			<th>rentalId</th>
			<th>filmTitle</th>
			<th>inventoryId</th>
			<th>name(customerId)</th><!-- name = firstName + lastName -->
			<th>rentalDate</th>
			<th>returnDate</th>
		</tr>
	</table>
	<form action="/sakila/d0325/rentalList.jsp">
		filmTitle Search Word : 
		<input type="text" name="searchWord">
		<button type="submit">검색</button>
	</form>
</body>
</html>