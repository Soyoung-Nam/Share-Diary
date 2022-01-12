<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 결과</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

h3 { text-align:center; font-size:20px; letter-spacing:5px; color: #CC3333; }

#contentbox {  /* 기존에 있던div를 contentbox로 바꿔서 변경했습니다 */	
	margin: 0 auto;
	margin-top: 30px;
	padding: 0;
	text-align: center;
}
#joinse {
	margin: 0 auto;
	margin-top:150px;
	padding:10px;
}
#lgbtn {
	width: 150px;
	border: 1px solid rgb(138, 43, 226);
	background-color: white;
	color: rgb(138, 43, 226);
	padding: 5px;
	border-radius: 25px;
	cursor: pointer;
}

#jibtn {
	width: 150px;
	border: 1px solid rgb(138, 43, 226);
	background-color: white;
	color: rgb(138, 43, 226);
	padding: 5px;
	border-radius: 25px;
	cursor: pointer;
}

#lgbtn:hover { background-color: #FFCCFF; color: rgb(138, 43, 226); }
#jibtn:hover { background-color: #FFCCFF; color: rgb(138, 43, 226); }
</style>
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>
<div id="contentbox">
<div id="joinsuccess">
	<img alt="logo" src="./img/logo1.jpg" border="0" width="200" height="200">
	<div id="joinse">
		<%
		if (request.getAttribute("count").equals(1)) { // join서블릿에서 받아오는 count 가입 성공시 1
		%>
			<h2><%=request.getAttribute("id") %>님,<br></h2> 
			<h3>가입이 완료되었습니다.</h3>
			<h3>가입하신 아이디로<br>로그인 해주세요.</h3>
			<button onclick="location.href='./login.jsp'" id="lgbtn">로그인 하러가기</button>
		<%
		} else { // 가입 실패시
		%>
			<h2><%=request.getAttribute("id") %>님,<br></h2> 
			<h3>문제가 발생했습니다.</h3><br>
			<h3>가입을 완료하지 못했습니다.<br>다시 시도해주세요.</h3>
			<button onclick="location.href='./join.jsp'" id="jibtn">회원가입 하러가기</button>
		<%
		}
		%>
	</div>
</div> 	<!-- joinsuccess -->
</div>  <!-- contentbox -->
</div>  <!-- article -->
</body>
</html>