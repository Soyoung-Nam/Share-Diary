<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
if (session.getAttribute("id") == null
	|| request.getAttribute("check") == null) {
	response.sendRedirect("./login.jsp"); //일단 다시 로그인으로 돌림
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호확인</title>
<style type="text/css">

#contentbox {  /* 기존에 있던div를 contentbox로 바꿔서 변경했습니다 */	
	margin: 0 auto;
	margin-top: 30px;
	padding: 0;
	text-align: center;
}

#infobox input[type=password] {
	height: 30px;
	margin: 5px;
	width: 195px;
	padding: 5px;
	border: 1px solid rgb(138, 43, 226);
	border-radius: 25px;
}

#infobox button[type=submit] {
	width: 130px;
	border: 1px solid rgb(138, 43, 226);
	background-color: white;
	color: rgb(138, 43, 226);
	padding: 5px;
	border-radius: 25px;
	cursor: pointer;
}

#infobox button[type=submit]:hover { background-color: #FFCCFF; color: rgb(138, 43, 226); }

</style>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
<!-- 개인정보보기 버튼 눌렀을때 비밀번호 입력을 안했거나 짧을 경우 -->
$(function(){
	$("#submit").click(function(){
		var pw = $("#pw").val();		
		if (pw == "" || pw.length < 4) {
			alert("비밀번호를 입력하세요.");
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
<!-- myinfo버튼 눌렀을때 비밀번호 입력으로 한번 더 확인하는 페이지-->
<div id="contentbox">
	<div id="infobox" style="color: rgb(138, 43, 226);">
		<img alt="logo1" src="./img/logo1.jpg" border="0" width="200" height="200">
		<form action="./myinfo" method="post">
		개인정보를 보기 위해<br>비밀번호를 입력하세요.<br> 
		비밀번호 : <input type="password" name="pw" id="pw" required="required">
		<button type="submit" id="submit">개인정보보기</button>
		</form>
	</div>
</div> <!-- contentbox -->
</div> <!-- article -->
</body>
</html>