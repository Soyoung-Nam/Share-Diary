<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${dto eq null }">
	<c:redirect url="./board"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<link href="./css/board.css" rel="stylesheet"> <!-- css파일 따로 만들었습니다 -->
<script type="text/javascript">
function select(){
	var category = document.getElementById("category").value;
	location.href='board?category='+category;
}
</script>
</head>
<body>

<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>

	<div id="contentbox">
		
		<div id="boardbox">
		
			<div id="searchbox">
				<!-- 모아보기, 검색 기능 -->
				<select onchange="select()" id="category">
					<option value="allcate">카테고리</option>
				
					<c:forEach items="${cateList }" var="i">
						<c:choose>
							<c:when test="${i eq '영화' }"><c:set var="cateEng" value="movie"/> </c:when>
							<c:when test="${i eq '일상' }"><c:set var="cateEng" value="daily"/> </c:when>
							<c:when test="${i eq '유머' }"><c:set var="cateEng" value="humor"/> </c:when>
						</c:choose>
						<c:if test="${i eq category }">
							<option value="${cateEng }" selected="selected">${i }</option>
						</c:if>
						<c:if test="${i ne category }">
							<option value="${cateEng }">${i }</option>
						</c:if>
					</c:forEach>
				</select>
			</div><!-- end of searchbox -->
			
			<c:choose>
				<c:when test="${fn:length(dto) > 0 }">
			
				<table>
				
				<tr class="th"><!-- 게시판 가장 윗줄, th -->
					<th>번호</th>
					<th>&nbsp;</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
					<th>조회수</th>
					<th>추천</th>
				</tr>
					<c:forEach items="${dto }" var="dto">
				<tr>
					<td>${dto.bno }</td>
					<!-- 썸네일 -->
					<td>
					<c:choose>
						<c:when test="${dto.bthumbnail eq null }">
							<img alt="thumb" src="./img/logo_noimage.png" id="thumb">
						</c:when>
						<c:otherwise>
							<img alt="thumb" src="./thumbnail/${dto.bthumbnail }" id="thumb">
						</c:otherwise>
					</c:choose>
					</td>
					
					<td style="text-align: left;">
					<a href="./boardDetail?bno=${dto.bno }"><small>(${dto.category })</small> ${dto.btitle }
						<!-- 댓글이 있으면 표시, 없으면 표시 안함 -->
						<c:if test="${dto.commentcount > 0}">
						<small>(${dto.commentcount })</small>
						</c:if>
					</a>
					</td>
					<td style="text-align: left;">${dto.name }</td>
					<td>${dto.bdate }</td>
					<td>${dto.bcount }</td>
					<td>${dto.blike }</td>
				</tr>
				</c:forEach>
				
				</table><!-- end of table -->
				</c:when>
			
				<c:otherwise>게시글이 없습니다.</c:otherwise>
			</c:choose>
			
			<!-- 페이징 -->
			<div id="paging">
				<c:set var="pageName" value="board" scope="request"/> <!-- scope="request"로 index값까지 주소줄에 전송 -->
				<c:set var="PAGENUMBER" value="10" scope="request"/>
				<c:import url="paging.jsp"/>
			</div>
	
			<!-- 글쓰기 버튼 --> <!-- 0810샛별 -->
			<c:if test="${sessionScope.id ne null }">
				<button id="writebtn" onClick="location.href='./boardWrite'">글쓰기</button>
			</c:if>
			
			<!-- 검색 -->
			<form action="board" method="post" id="searchboard"> <!-- 0813 소영 -->
				<select name="searchvalue">
					<option value="btitle">제목</option>
					<option value="bcontent">내용</option>
					<option value="name">글쓴이</option>
				</select>
				<input type="text" id="searchtext" name="searchtext">
				<button type="submit" id="submitBtn">검색</button> <!-- 0813 소영 -->
			</form>
			
		</div> <!-- end of boardbox -->
	</div> <!-- end of contentbox -->
</div> <!-- end of article -->
</body>
</html>