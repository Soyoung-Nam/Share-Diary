<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">
/*   joinPage: 회원가입 전체 div
	 joinaction: 회원가입 입력란 전체 div
	 joinid p태그 아이디 div
	 joinname p태그 닉네임 div
	 joinpassword  p태그 비밀번호 div
	 joinemail p태그 이메일 div
	 joinbirth p태그 생년월일 div 
	 joinIA 가입하기 버튼이랑 지우기 버튼 전체 div
	 joinbtn p태그 가입하기 버튼 
	 resetbtn p태그 지우기 버튼 */

#h1 { text-align:center; font-size:15px; letter-spacing:10px; color: rgb(138, 43, 226); }

#contentbox {  /* 기존에 있던div를 contentbox로 바꿔서 변경했습니다 */	
	margin: 0 auto;
	margin-top: 30px;
	padding: 0;
	text-align: center;
}

#joinaction p input{
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

#joinIA {
	margin: 0 auto;
	padding: 0;
	width: 300px;
	height: 200px;
}

#joinbtn {
	border: 1px solid rgb(138, 43, 226);
	background-color: white;
	color: rgb(138, 43, 226);
	padding: 5px;
	border-radius: 25px;
	cursor: pointer;
	text-align: center;
	width: 230px;
	height: 35px;
	float: right;
}

#resetbtn {
	border: 1px solid rgb(138, 43, 226);
	background-color: white;
	color: rgb(138, 43, 226);
	padding: 5px;
	border-radius: 25px;
	cursor: pointer;
	text-align: center;
	float: left;
	width: 60px;
	height: 35px;
}

#joinid, #joinname, #joinpassword, #joinemail, #joinbirth {
	padding: 10px;
	font: bold;
	color: rgb(138, 43, 226);
	font-size: 15px;
}

#same { padding-left: 80px; }

#img {
	text-align: center;
	max-width: auto;
	height: auto;
}

#joinbtn:hover { background-color: #FFCCFF; color: rgb(138, 43, 226); }
#resetbtn:hover { background-color: #FFCCFF; color: rgb(138, 43, 226); }

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<script type="text/javascript">
function join() { // 가입하기 버튼 눌렀을 때
	var id = document.getElementById("id");
	var name = document.getElementById("name");
	var pw1 = document.getElementById("pw1");
	var pw2 = document.getElementById("pw2");
	var email = document.getElementById("email");
	var date2 = document.getElementById("date");
	
	// 아이디가 빈칸이거나 4글자 이하일 때
	if (id.value == "" || id.value.length < 5) {
		alert("5글자 이상으로 된 아이디를 입력해주세요.");
		id.focus();
		id.style.backgroundColor = '#FFCCFF';
		return false;
	}
	// 닉네임이 빈칸이거나 2글자 이하일 때
	if (name.value == "" || name.value.length < 2) {
		alert("닉네임을 입력해주세요.");
		name.focus();
		name.style.backgroundColor = '#FFCCFF';
		return false;
	}
	// pw1 확인
	if (pw1.value == "" || pw1.value.length < 5) {
		alert("6~10자 비밀번호를 입력해주세요.");
		pw1.focus();
		return false;
	}
	// pw2 확인
	if (pw2.value == "" || pw2.value.length < 5) {
		alert("6~10자 비밀번호를 입력해주세요.");
		pw2.focus();
		return false;
	}
	// pw1과 pw2가 일치한지 확인
	if (pw1.value != pw2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		pw1.value = "";
		pw2.value = "";
		pw1.style.backgroundColor = '#FFCCFF';
		pw2.style.backgroundColor = '#FFCCFF';
		pw1.focus();
		return false;
	}
	// 이메일이 빈칸일 때 
	if (email.value == "") {
		alert("email주소를 입력해주세요.");
		email.style.backgroundColor = '#FFCCFF';
		email.focus();
		return false;
	}
	// 생년월일이 빈칸일 때
	if(date.value == ""){
		alert("생년월일을 입력해주세요.");
		date.style.backgroundColor = '#FFCCFF';
		date.focus();
		return false;
	}
}
// 비밀번호 입력칸 아래에 일치/불일치 텍스트로 표시
function Same() {
    if(document.getElementById('pw1').value !='' && document.getElementById('pw2').value !='') {
        if(document.getElementById('pw1').value == document.getElementById('pw2').value) {
        	document.getElementById('same').innerHTML='비밀번호가 일치합니다.';
            document.getElementById('same').style.color='rgb(75, 0, 130)';
        }
        else {
            document.getElementById('same').innerHTML='비밀번호가 일치하지 않습니다.';
            document.getElementById('same').style.color='red';
        }
    }
}

// 회원가입화면 로딩되자마자 가입하기 버튼 비활성화
$(function(){
	$("#joincom").prop("disabled", true);
});

// 아이디 중복확인 alert로 알림.
function checkID(){
	var id = $("#id").val();
	if(id == "" || id.length < 5){
		alert("아이디를 입력해주세요.");
		$("#id").focus();
		return false;
	}
	$.ajax({
		type:'post',
		dataType:'text',
		data: 'id='+id,
		url: './idCheck',
		success: function(rData, textStatus, xhr){
			if(rData == 1){
				alert("이미 사용중인 아이디입니다.");
				$("#joinbtn").prop("disabled", true);
			}else{
				alert(id + "는 사용 할 수 있습니다.");
				$("#joinbtn").prop("disabled", false);	
			}
		},
		error: function(xhr, status, e){
			alert("문제 발생 : " + e);
		}
	});
}
</script>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>
<div id="contentbox">
<div id="joinPage">
	<div id=img>
		<img alt="text" src="./img/text.jpg" border="0" width="400" height="200">
	</div>
	<div id=h1>
		<h1>회원가입</h1>
	</div>
		<form action="./join" method="post" onsubmit="return join()">
			<div id="joinaction">
				<p id="joinid" class="joinus"> <!-- 아이디 입력창 -->
					<span class="joinM">아이디</span> 
					<input type="text" name="id" id="id" onchange="checkID()">
				</p>
				<p id="joinname" class="joinus"> <!-- 닉네임 입력창 -->
					<span class="joinM">닉네임</span>
					<input type="text" name="name" id="name">
				</p>
				<p id="joinpassword" class="joinus"> <!-- 비밀번호 입력창 -->
					<span class="joinM">비밀번호</span> 
					<input type="password" id="pw1" name="pw1" class="jpw1" placeholder="비밀번호를 입력해주세요.">
					<br>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; <!-- pw1과 pw2 간격이 너무 붙어서 띄웠습니다. -->
					<br>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; <!-- pw1과 pw2 간격이 너무 붙어서 띄웠습니다. -->
					<input type="password" id="pw2" name="pw2" class="jpw2" placeholder="비밀번호 확인" onchange="Same()">
					<br><span id="same"></span>
				</p>
				<p id="joinemail" class="joinus"> <!-- 이메일 입력창 -->
					<span class="joinM">이메일</span> 
					<input type="email" name="email" id="email">
				</p>
				<p id="joinbirth" class="joinus"> <!-- 생년월일 입력창 -->
					<span class="joinM">생년월일</span>
					<input type="date" name="date" id="date">
				</p>
			</div>
			<div id="joinIA">
					<button type="submit" id="joinbtn">가입하기</button> <!-- 가입하기 버튼 -->
				<p id="resetA">
					<button type="reset" id="resetbtn">지우기</button> <!-- 지우기 버튼 -->	
				</p>
			</div>
		</form>
</div> <!-- joinpage -->
</div> <!-- contentbox -->
</div> <!-- article -->
</body>
</html>