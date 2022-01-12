<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 0806샛별 1시작 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${boardList eq null || openDiaryList eq null}">
	<c:redirect url="./index"/>
</c:if>
<!-- 0806샛별 1끝 -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>index</title>
<!-- 부트스트랩 CSS추가 -->
<link rel="stylesheet" href="./css/bootstrap.min.css">
<!-- 커스텀CSS추가 -->
<link rel="stylesheet" href="./css/custom.css">

<link href="./css/index.css" rel="stylesheet">
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/board.css" rel="stylesheet">
<link href="./css/boardWrite.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">


<style type="text/css">

</style>
</head>

<body>
<div id="banner">
<c:import url="banner.jsp"/></div>

<div id="article">
<div id="menu">
<c:import url="menu.jsp"/></div>

		<!-- main : 페이지 넣어주세요 -->
				<div class="contentbox">			

				<div class="mainBoardBox1">
					<div id=boardline><a href="./OpendiaryView">공유일기장</a></div>
						<c:forEach items="${openDiaryList}" var="DiaryList">
						
					<div id=OpendiaryBox>
                  <a href="./odDetail?dNo=${DiaryList.no }">${DiaryList.title }
                     <br>${DiaryList.date }
                     <br> ${DiaryList.feel } day        
                     </a>
                        </div>
                  </c:forEach>

				</div><!-- mainBoardBox1 -->

            <div class="mainBoardBox">
               <div id=boardline><a href="./board">게시판</a></div>
               <ul id="ulTable">
               <li>
                  <ul>
                     <li>제목</li>
                     <li>날짜</li>
                     <li>추천수</li>
                  </ul>
               </li>
                  <c:forEach items="${boardList }" var="list">
                     <li>
                        <ul>
                           <li> <!-- 썸네일 --> 
                     <c:choose>
                              <c:when test="${list.bthumbnail eq null }">
                               <img alt="thumb" src="./img/logo_noimage.png" id="thumb">
                              </c:when>
                           <c:otherwise>
                              <img alt="thumb" src="./thumbnail/${list.bthumbnail }" id="thumb">
                           </c:otherwise>
                        </c:choose>
                           <!-- 제목 --> 
                     <a href="./boardDetail?bno=${list.bno }">${list.btitle }</a>
                        <c:if test="${list.commentcount > 0}">
                     <small>(${list.commentcount })</small>
                        </c:if>
                        </li>
                     <li> ${list.bdate }</li>
                     <li>${list.blike }</li>
                     </ul> 
                     </li>
                  </c:forEach>
               </ul>
            </div>
            <br>
         </div>
         </div>

</body>
</html>
