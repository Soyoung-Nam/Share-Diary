<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script>
	function nameApply(){
		var userNameApply = document.userNameApply;
		var userName = userNameApply.userName.value;
		userNameApply.method = "post";
		userNameApply.action = "PartnerApply";
		userNameApply.submit();
	}
</script>

<title></title>
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>
<div id="contentbox" style="display:flex; flex-direction:row;">


<div style="display:flex; flex-direction:row; width:500px; height:600px;">
<c:forEach items="${dto}" var="dto">
	
	
	<c:choose >
		<c:when test= "${dto.no % 2 == 1 }">
			<c:set var = "color1" value = "#e8b8c8" />
			<c:set var = "color2" value = "#d5d7e1" />
		</c:when>
		<c:otherwise>
			<c:set var = "color1" value="#d5d7e1"/>
			<c:set var = "color2" value="#9e90c4"/>
		</c:otherwise>
	</c:choose>
		
			<div style=" width:300px; height: 10%; background: linear-gradient(to bottom right, ${color1}, ${color2}); color:white; border-radius: 2em;">
			<div>보낸 사람: ${dto.send }</div><div><a href='./PartnerAccept?send=${dto.send }'>받아들이기</a></div>
			<br>
			<br>
			
			</div>
</c:forEach>
			<c:if test="${dto eq null }">
				<p>교환일기 신청이 없습니다.</p>
			</c:if>


</div>

<div>
	<form name= "userNameApply">
		<input type="text" name="userName" placeholder="닉네임을 넣어주세요!"/>
		<input type="submit"  value="신청하기" onclick="javascript:nameApply()"/>
	</form>
	
	
	
	
	





</div>

</div>
</div>

</body>
</html>