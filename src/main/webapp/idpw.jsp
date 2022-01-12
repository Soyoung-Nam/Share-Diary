<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style type="text/css">
#idpw h2 {
	color: rgb(138, 43, 226);
}

#text {
	text-align:center; font-size:15px; letter-spacing:5px; color: #CC3333;
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
	width: 150px;
	border: 1px solid rgb(138, 43, 226);
	background-color: white;
	color: rgb(138, 43, 226);
	padding: 5px;
	border-radius: 25px;
	cursor: pointer;
}

#idfind {
	margin: 0 auto;
	margin-top:150px;
	padding:10px;
}
#idpw button:hover { background-color: #FFCCFF; color: rgb(138, 43, 226); }
</style>
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>

<%
String result = (String) request.getAttribute("result"); // result값으로 제어하기때문에 result값 뽑기
%>
<div id="contentbox">
	<div id="idpw">
		<%
		if (result == null) { // result값이 null일때 이메일입력해서 아이디찾기
		%>
			<img id="logo" alt="logo2" src="./img/background1.jpeg" border="0" width="400" height="200">
			<div id="text">
				계정을 찾으시려면 이메일 주소를 입력하세요.
			</div>
			<form action="./idpw" method="post" onsubmit="return email()">
				<input type="email" name="email" id="email" required="required">
				<button type="submit"><img alt="password" src="./img/password1.png" style="vertical-align: text-bottom;">아이디 찾기</button>
			</form>
		<%
		} else if (result.equals("1")) { // result값이 1일때 아이디를 알려주고 비밀번호 찾기or로그인하러가기 버튼 띄우기
		%>
			<h1>가입하신 ID는<br> <%=request.getAttribute("id") %>입니다.</h1>
			<button onclick="location.href='./password.jsp'"><img alt="password" src="./img/password1.png" style="vertical-align: text-bottom;">비밀번호 찾기</button>
			<button onclick="location.href='./login.jsp'"><img alt="login" src="./img/login1.png" style="vertical-align: text-bottom;">로그인 하러가기</button>
		
		<%
		} else if (result.equals("2")) { // result값이 2일때 가입된 이메일 없으므로 가입요청하기
		%>
			<h1>해당 email로 가입된 ID가 없습니다.</h1><br>
			<h1>해당 email로 가입하시거나,</h1><br>
			<h1>다시 시도해주세요.</h1>
 		
 		<%} else if (result.equals("3")) { // result값이 3일때 비밀번호 변경 성공
 		%> 
			<h1>비밀번호가 변경되었습니다.</h1><br>
			<h2>변경된 비밀번호로 다시 로그인 해주세요.</h2> 
			
 			<button onclick="location.href='./login.jsp'">로그인하러 가기</button> 
			
		<%} else if (result.equals("4")) { 
			// result값이 4일때 비밀번호 변경 실패 닉네임이 다르거나 아이디가 틀리거나 비밀번호 1,2차가 다를경우 나옴.
		%>
			<h1>비밀번호를 변경할 수 없습니다.</h1><br>
			<h2>입력된 정보가 올바르지 않습니다.</h2><br>
			<h2>확인 후 다시 시도하세요.</h2>
		
		<%} else if (result.equals("5")) { // result값이 5는 에러
		%>
			<h1>입력된 정보가 올바르지 않습니다.</h1><br>
			<h2>문제가 발생하여 정보가 전송되지 않았습니다.</h2>
		<%} %>	
	</div>
</div> <!-- contentbox -->
</div> <!-- article -->
</body>
</html>