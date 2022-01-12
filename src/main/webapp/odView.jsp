<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>

<c:if test="${dto eq null }">
	<c:redirect url="./OpendiaryView"/>
</c:if>

<meta charset="utf-8">
<link rel="stylesheet" href="menu.css">
<title>Insert title here</title>
</head>
<body>

<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>


<div id="contentbox" style="display:flex; flex-direction:column;">
<c:forEach items="${dto}" var="dto">
<br>
<div align="center" style="width:600px; height: 100%;  border-radius: 0.5em; background: linear-gradient(to bottom right,#e6e3e0, #9e90c4);">
		<a href="./odDetail?dNo=${dto.no }">
		<div>${dto.title }</div>
		<div>날짜: ${dto.date }</div>
		<div>이 날의 기분: ${dto.feel }</div>
		</a>
</div>

</c:forEach>
			<div id="paging">
				<c:set var="pageName" value="OpendiaryView" scope="request"/> <!-- scope="request"로 index값까지 주소줄에 전송 -->
				<c:set var="PAGENUMBER" value="10" scope="request"/>
				<c:import url="paging.jsp"/>
			</div>


</div>
</div>





</body>
</html>