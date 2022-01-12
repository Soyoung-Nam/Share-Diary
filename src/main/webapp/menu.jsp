<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트스트랩 CSS추가 -->
<link rel="stylesheet" href="./css/bootstrap.min.css">
<!-- 커스텀CSS추가 -->
<link rel="stylesheet" href="./css/custom.css">
<link href="./css/menu.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<script type="text/javascript" src="./js/menu.js"></script>


<!-- container : 본문 페이지
<div class="container"> -->
<!-- side : 로그인 들어가는 사이드 -->
   <div class="side">
      <div class="login">
         <c:choose>
            <c:when test="${sessionScope.id ne null }">
               ${sessionScope.id }님 <br>반갑습니다
               
               <button onclick="location.href='./ExchangeDiaryView'" id="log">교환일기장</button>
               <button onclick="location.href='./myinfo'" id="log">My Info</button>
               <button onclick="return logout()" id="log">로그아웃</button>
               
               
            </c:when>
            <c:otherwise>
               <p style="color:grey; margin-top:10px; font-size:20px;">Share with people</p>
               <div id="menu">
                  <button type="button" onclick="location.href='login.jsp'" id="log"> 로그인 </button>
               </div>
               <p style="color:grey;"><a href="./join.jsp"><img src="./img/join.png;" style="width:16px;"> 회원가입 </a></p>
               <p style="color:grey;"><a href="./idpw.jsp"><img src="./img/lock.png"> 아이디 찾기 | 비밀번호 찾기 </a></p>
            </c:otherwise>
         </c:choose>      
      </div><!-- end of login -->
   <c:import url="calendar.jsp"/>
   </div><!-- end of side -->
<!-- </div> end of container -->