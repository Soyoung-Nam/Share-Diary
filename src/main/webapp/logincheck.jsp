<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 확인용</title>
<style type="text/css">

h1 { text-align:center; font-size:15px; letter-spacing:5px; color: #CC3333; }

#contentbox {  /* 기존에 있던div를 contentbox로 바꿔서 변경했습니다 */	
	margin: 0 auto;
	margin-top: 30px;
	padding: 0;
	text-align: center;
}

#idin input {
	width: 250px;
	height: 35px;
	line-height: 35px;
	margin: 0 10px;
	padding: 0 15px;
	border: 1px solid rgb(138, 43, 226);
	border-radius: 25px;
	position:relative;
	background-color: white;
}

#pwin input {
	width: 250px;
	height: 35px;
	line-height: 35px;
	margin: 0 10px;
	padding: 0 15px;
	border: 1px solid rgb(138, 43, 226);
	border-radius: 25px;
	position:relative;
	background-color: white;
}

#loginbt button { /* 로그인 버튼 */
	width: 250px;
	border: 1px solid rgb(138, 43, 226);
	background-color: white;
	color: rgb(138, 43, 226);
	padding: 5px;
	border-radius: 25px;
	cursor: pointer;
	text-align: center;
}

#idpwbt button { /* 아이디/비밀번호찾기 버튼 */
	width: 250px;
	border: 1px solid rgb(138, 43, 226);
	background-color: white;
	color: rgb(138, 43, 226);
	padding: 5px;
	border-radius: 25px;
	cursor: pointer;
	text-align: center;
}

#loginbtn:hover { background-color: #FFCCFF; color: rgb(138, 43, 226); }
#idpwbtn:hover { background-color: #FFCCFF; color: rgb(138, 43, 226); }
</style>
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>
<!-- 로그인 잘 되는지 확인차 만들어 둔 페이지 입니다. -->
<div id="contentbox">
<div id="loginbox">
	<c:choose>
		<c:when test="${sessionScope.id ne null }">
		<!-- 로그인 성공 시 ~~님 반갑습니다. 라고 출력 -->
			${sessionScope.name }님<br>반갑습니다.
			<button onclick="location.href='./myinfo'" style="cursor:pointer;">My Info</button>
			<button onclick="return logout()" style="cursor:pointer;">로그아웃</button>
		</c:when>
		<c:otherwise>
		<!-- 로그인 실패 시 다시 로그인 하도록 로그인창 출력 -->
		<img id="logo" alt="logo2" src="./img/background1.jpeg" border="0" width="400" height="200">
		<h1>아이디 또는 비밀번호를<br>확인해주세요.</h1>
		<form action="./login" method="post">
			<div id="idin"> <!-- 아이디 입력창 -->
				<input type="text" name="id" id="id" placeholder="아이디를 입력하세요." required="required">
			</div><br>
			<div id="pwin"> <!-- 비밀번호 입력창 -->
			<input type="password" name="pw" id="pw" placeholder="비밀번호를 입력하세요." required="required"> 
			</div><br>
			<div id="loginbt">
			<button id="loginbtn" style="cursor:pointer;"><img alt="login" src="./img/login1.png" style="vertical-align: text-bottom;">Login</button>
			</div>
		</form>
		<br>
		<div id="idpwbt"> <!-- ID/PW찾기 버튼 -->
			<form action="./idpw.jsp" method="post">
				<button id="idpwbtn"><img alt="password" src="./img/password1.png" style="vertical-align: text-bottom;">ID/PW 찾기</button> 
			</form>
		</div>
		</c:otherwise>
	</c:choose><br>
</div> <!-- contentbox -->
</div> <!-- loginbox -->
</div> <!-- article -->
<script>
function logout() {
	if (confirm("로그아웃 하시겠습니까?")) {
		//alert("확인용");
		location.href="logout";
	} 
}
</script>