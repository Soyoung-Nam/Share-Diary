<%@ page import="shareDiary.dto.LoginDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
if (request.getAttribute("check") == null
	|| session.getAttribute("id") == null
	|| session.getAttribute("name") == null
	|| request.getAttribute("dto") == null) {
	response.sendRedirect("./error.jsp");
}
LoginDTO dto = (LoginDTO)request.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my info</title>
<style type="text/css">

#contentbox {  /* 기존에 있던div를 contentbox로 바꿔서 변경했습니다 */	
	margin: 0 auto;
	margin-top: 30px;
	padding: 0;
	text-align: center;
}

#infobox input {
	height: 30px;
	margin: 5px;
	width: auto;
	padding: 5px;
	border: 1px solid rgb(138, 43, 226);
	border-radius: 25px;
}

#submitbtn {
	width: 270px;
	border: 1px solid rgb(138, 43, 226);
	background-color: white;
	color: rgb(138, 43, 226);
	padding: 5px;
	border-radius: 25px;
	cursor: pointer;
}

#submitbtn:hover { background-color: #FFCCFF; color: rgb(138, 43, 226); }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
//비밀번호 변경 버튼 클릭시 
$(function(){
	$("button").click(function(){
		alert("비밀번호가 변경되었습니다.\n다시 로그인해주세요.");
		var pw = $("#pw").val();
		if (pw == "") {
			alert("비밀번호를 입력하세요.");
			$("#pw").focus();
			return false;
		}
		if (pw.length < 4) {
			alert("비밀번호의 길이가 짧습니다.");
			$("#pw").focus();
			return false;
		}
	});
});
</script>
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>
<div id="contentbox">
	<div id="infobox" style=" color: rgb(138, 43, 226);">
		<div id="ipbox">
		<img alt="logo1" src="./img/logo1.jpg" border="0" width="200" height="200"><br>
		ID : <input type="text" value="<%=dto.getId() %>" disabled="disabled">
		<br>
		email : <input type="text" value="<%=dto.getEmail() %>" disabled="disabled">
		<br>
		닉네임 : <input type="text" value="<%=dto.getName() %>" disabled="disabled">
		<br> <!-- 비밀번호만 바꿀수 있도록 위의 세개는 다 disabled처리 -->
		<form action="./changepw" method="post" onsubmit="return password()">
		비밀번호 : <input type="password" name="pw" id="pw">
		<br>
		<button type="submit" id="submitbtn">비밀번호 변경</button>
		</form>
		</div>
	</div>
	<div> 
		
	</div>
</div> <!-- contentbox -->
</div> <!-- article -->
</body>
</html>