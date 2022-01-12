<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
<style type="text/css">
#text {
	width: 300px;
}

#idpw h2 {
	color: rgb(138, 43, 226);
}

#contentbox {  /* 기존에 있던div를 contentbox로 바꿔서 변경했습니다 */	
	margin: 0 auto;
	margin-top: 30px;
	padding: 0;
	text-align: center;
}

#idpw input {
	height: 30px;
	margin: 5px;
	width: 300px;
	padding: 5px;
	border: 1px solid rgb(138, 43, 226);
	border-radius: 25px;
}
#idpw button {
	width: 120px; /* 120으로 변경했습니다. */
	border: 1px solid rgb(138, 43, 226);
	background-color: white;
	color: rgb(138, 43, 226);
	padding: 5px;
	border-radius: 25px;
	cursor: pointer;
}
#img {
	text-align: center;
	width: auto;
	height: auto;
}
#idfind {
	margin: 0 auto;
	margin-top:150px;
	padding:10px;
}
h1 { text-align:center; font-size:15px; color: #CC3333; }
</style>
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>
<%
String result = (String) request.getAttribute("result");
%>
<div id="contentbox">
	<div id="idpw">
		<%
		if (result == null) {
		%>
		<div id="text">
			<h1>비밀번호를 잃어버리셨나요?</h1>
			사용하시던 닉네임과 ID, 입력 후 변경하실 비밀번호를 입력해주세요.
			<form action="./idpw" method="post">
  				<input type="text" name="name" id="name" placeholder="닉네임을 입력하세요"> 
 				<input type="text" name="id" id="id" placeholder="ID를 입력하세요">
				<input type="password" name="pw1" id="pw1" placeholder="비밀번호를 입력하세요">
				<input type="password" name="pw2" id="pw2" placeholder="같은 비밀번호를 입력하세요"><br>
				<button type="submit">비밀번호 설정</button>
			</form>
		</div>
		<%} %>
	</div> <!-- idpw -->
</div> <!-- contentbox -->
</div> <!-- article -->
</body>
</html>