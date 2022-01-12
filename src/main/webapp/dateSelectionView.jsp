<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<c:if test="${dto eq null }">
	<!-- dto가 없다면 서블릿으로 던지기 -->
	<c:redirect url="./DateSelectionView"/>
</c:if>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="menu.css">
<title>Insert title here</title>
</head>
<body>

<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>



<div id ="contentbox">
<c:forEach items="${dto}" var="dto">
		<a href="./detail?dNo=${dto.no }">
		<div align="center" style="width:60%; height: 100px; background: linear-gradient(to bottom right,#e6e3e0, #9e90c4); color:white; border-radius: 0.5em;">
		<div>${dto.title }</div>
		<div>날짜: ${dto.date }</div>
		<div>이 날의 기분: ${dto.feel }</div>
		</div>
		</a>
</c:forEach>
<input type = "button" onclick="location.href='diaryView.jsp'" value= "돌아가기"/>
</div>
</div>

</body>
</html>