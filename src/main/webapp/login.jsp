<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style type="text/css">

#contentbox {  /* 기존에 있던div를 contentbox로 바꿔서 변경했습니다 */	
	margin: 0 auto;
	margin-top: 30px;
	padding: 0;
	text-align: center;
}

#idin input { /* 아이디 입력인풋 */
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

#pwin input { /* 비밀번호 입력인풋 */
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

#btbox { /* 버튼 박스 */
	margin: 0 auto;
	width: 250px;
	padding: 0;
	text-align: center;
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
	width: 120px; /* 120으로 변경 */
	border: 1px solid rgb(138, 43, 226);
	background-color: white;
	color: rgb(138, 43, 226);
	padding: 5px;
	border-radius: 25px;
	cursor: pointer;
	text-align: center;
	float: left;
}

#joinbt button { /* 회원가입 버튼 */
	width: 110px;
	border: 1px solid rgb(138, 43, 226);
	background-color: white;
	color: rgb(138, 43, 226);
	padding: 5px;
	border-radius: 25px;
	cursor: pointer;
	text-align: center;
	float: right;
}

#loginbtn:hover { background-color: #FFCCFF; color: rgb(138, 43, 226); }
#idpwbtn:hover { background-color: #FFCCFF; color: rgb(138, 43, 226); }
#joinbtn:hover { background-color: #FFCCFF; color: rgb(138, 43, 226); }

</style>
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>
<div id="contentbox">
<div id="loginbox">
	<img id="logo" alt="logo2" src="./img/background1.jpeg" border="0" width="400" height="200">
	<form action="./login" method="post">
		<div id="idin"> <!-- 아이디 입력창 -->
			<input type="text" name="id" id="id" placeholder="아이디를 입력하세요." required="required">
		</div>
		<br>
		<div id="pwin"> <!-- 비밀번호 입력창 -->
			<input type="password" name="pw" id="pw" placeholder="비밀번호를 입력하세요." required="required"> 
		</div>
		<br>
			<div id="loginbt"> <!-- 로그인 버튼 -->
				<button id="loginbtn"><img alt="login" src="./img/login1.png" style="vertical-align: text-bottom;">로그인</button> 
			</div>
	</form>
		<div id="btbox">
		<br>
			<div id="idpwbt"> <!-- ID/PW찾기 버튼 -->
				<form action="./idpw.jsp" method="post">
					<button id="idpwbtn"><img alt="password" src="./img/password1.png" style="vertical-align: text-bottom;">ID/PW 찾기</button> 
				</form>
			</div>
			<div id="joinbt"> <!-- 회원가입 버튼 -->
				<form action="./join.jsp" method="post">
					<button id="joinbtn"><img alt="join" src="./img/join1.png" style="vertical-align: text-bottom;">회원가입</button> 
				</form>
			</div>
		</div>
</div> <!-- loginbox -->
</div> <!-- contentbox -->
</div> <!-- end of article -->
</body>
</html>