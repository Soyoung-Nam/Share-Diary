<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${id eq null }">
	<c:redirect url="./login.jsp"/>
</c:if>

<c:if test="${dto eq null }">
	<!-- dto가 없다면 서블릿으로 던지기 -->
	<c:redirect url="./ExchangeDiaryView"/>
</c:if>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="menu.css">

<script>
function check() {
	var check = document.check;
	check.method() = "post";
	check.action() = "PartnerCheck"; 
	check.submit();
	}
</script>




<title>Insert title here</title>
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>
<div id="contentbox" align="center" style="display:flex; flex-direction:column; ">
<c:forEach items="${dto}" var="dto">
	
<div style="disrplay:flex; flex-direction:row;">
<div align="right" style = "width:100px"><button onclick ="location.href = ('./EdDetail');">답글쓰기</button></div>
<div align="right" style = "width:100px"><button onclick ="location.href = ('./PartnerCheck');">파트너 관리</button></div>
</div>
	<c:choose >
		<c:when test= "${dto.recieve == id }">
			<c:set var ="direc" value = "left"/>
			<c:set var = "color1" value = "#e8b8c8" />
			<c:set var = "color2" value = "#d5d7e1" />
		</c:when>
		<c:otherwise>
			<c:set var ="direc" value = "right"/>
			<c:set var = "color1" value="#d5d7e1"/>
			<c:set var = "color2" value="#9e90c4"/>
		</c:otherwise>
	</c:choose>
			<div align=${direc } style=" width:500px; height: 100px; background: linear-gradient(to bottom right, ${color1}, ${color2}); color:white; border-radius: 2em;">
			<br>
			<br>
			<div>${dto.title }</div>
			<div>날짜: ${dto.date }</div>
			<div>이 날의 기분: ${dto.feel }</div>
			<div>이 날의 날씨: ${dto.weather }</div>
			</div>
			
</c:forEach>

</div>
</div>

</body>
</html>