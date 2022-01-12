<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Detail</title>
<link href="./css/boardDetail.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript" src="./js/boardDetail.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	//수정하기 기능
	$("button[name='modifycomm']").click(function(e) {
		var comment = e.target.closest(".modifyInput");
		console.log(comment);
		var bcontent = $(comment).children(".bcontent").text();
		var bno = $(comment).children(".bno").text();
		var bcno = $(comment).children(".bcno").text();
		$(comment).parent().html(
			"<form action='./commentModify' method='post'>"
			+"<textarea name='content'>"+bcontent+"</textarea>"
			+"<input type='hidden' name='bcno' value='"+bcno+"'>"
			+"<input type='hidden' name='bno' value='"+bno+"'>"
			+"<button>수정하기</button>"
			+"</form>"
			+"<div class='clear1'>"
			+"<button name='modifycancle'>수정취소</button>"
			+"</div>");
		$("button[name='modifycancle']").click(function(){ /* 수정하기를 취소했을 때 */
			location.reload();
		});
	});
});

<!-- 댓글쓰기 -->
function check(){
	var comment = $("#comment1").val();
	if(comment.length < 4){
		alert("댓글은 5자 이상이어야 합니다.");
		$("#comment1").focus();
		return false;
	}
}

<!-- 댓글삭제 -->
function commentDelete(bcno, bno) {
	if(confirm("댓글을 삭제하겠습니까?")){
		location.href='./commentDelete?bcno='+bcno+"&bno="+bno;
	}
}

<!-- 댓글 좋아요 -->
function likeUp(bcno, bno) {
	location.href='./likeUp?bcno='+bcno+"&bno="+bno;
}

<!-- 게시글 좋아요 -->
function likeUp1(bno) {
	location.href='./likeUp1?bno='+bno+'&value=blike';
}

<!-- 게시글 싫어요 -->
function likeDown(bno) {
	location.href='./likeUp1?bno='+bno+'&value=bhate';
}

<!-- 게시글 삭제 -->
function del(bno){
	if(confirm("삭제하시겠습니까?")){
		location.href='delete?bno='+bno;
	}
}

<!-- 게시글 수정 -->
function modify(bno){
	if(confirm("수정하시겠습니까?")){
		location.href="./boardModify?bno="+bno;
	}
}
</script>
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>

<div id="contentbox">
	<div id ="detail"> <!-- 상세보기 가져오기 -->
	
			<div id="title">
 				<b>제목 : </b> ${dto.btitle } <!-- 0813 소영 -->
				<c:if test="${dto.id eq sessionScope.id}"> <!-- 0810샛별 -->
 					<button onclick="del(${dto.bno })" id="boardDelBtn">삭제하기</button> <!-- 0813 소영 -->
					<button onclick="modify(${dto.bno })" id="boardModiBtn">수정하기</button> <!-- 0813 소영 -->
				</c:if>
			</div> <!-- end of title -->
			
			<div id="infobox">
			<div id="writer">
				<b>작성자 : </b> ${dto.name}(${dto.id }) <!-- 0813 소영 -->
			</div> <!-- end of writer -->
			
			<div id="date">
				<b>작성시간 : </b>${dto.bdate } <!-- 0813 소영 -->
			</div> <!-- end of date -->
			
			<div id="count">
				<b>조회수 : </b>${dto.bcount } <!-- 0813 소영 -->
			</div> <!-- end of count -->
			
			<button onclick="likeUp1(${dto.bno })" id="likeUpBtn"> 좋아요! 👍🏻 ${dto.blike }</button> <!-- 0813 소영 -->
			<button onclick="likeDown(${dto.bno })" id="likeDownBtn">️ 싫어요! 👎🏻 ${dto.bhate }</button> <!-- 0813 소영 -->
			
			</div> <!-- end of infobox -->
			
			<div id="content">
				<c:if test="${dto.bfilename ne null }">
					<img alt="${dto.bfilename }" src="./upload/${dto.bfilename }" width="40%" style="margin: 10px;"> <br>
				</c:if>
				${dto.bcontent }
			</div> <!-- end of content -->
			
		<!-- 댓글 가져오기 -->
		<c:if test="${dto.commentcount > 0}">
		▾${dto.commentcount }개의 댓글이 있습니다. <!-- 0813 소영 -->
		</c:if>
		<hr>
		<!-- 댓글 삭제하기 수정하기 -->
		<c:choose>
			<c:when test="${fn:length(commentList) > 0 }">
				<c:forEach items="${commentList }" var="i">
					<div id="commentinfo">
						<div id="commentid">
							${i.bcno } / ${i.name }(<small>${i.id }</small>)
						</div> <!-- end of commentid -->
						<div id="commentdate">
					 		${i.bcdate } / <button onclick="likeUp(${i.bcno }, ${i.bno })" id="likeUpBtn2"> 👍🏻 ${i.bclike }</button> <!-- 0813 소영 -->
					 	</div> <!-- end of commentdate -->
					 </div> <!-- end of commentinfo -->
					 	<div class="modifyBox">
					 	<div class="modifyInput">
					 	<div class="bcontent">${i.bccontent }</div>
					 	<div class="bno" style="display: none;">${i.bno }</div>
					 	<div class="bcno" style="display: none;">${i.bcno }</div>
					 
					 	<c:choose>
					 		<c:when test="${i.id eq sessionScope.id }">
					 			<button onclick="commentDelete(${i.bcno }, ${i.bno })" id="commentDeleteBtn">삭제하기</button> <!-- 0813 소영 -->
					 			<button name="modifycomm" id="commentModifyBtn">수정하기</button> <!-- 0813 소영 -->
							</c:when>
					 		<c:otherwise> <!-- 0810샛별 -->
					 			<!-- 로그인 세션 되면 삭제하기! 
					 			<button onclick="commentDelete(${i.bcno }, ${i.bno })">삭제하기</button>
					 			<button name="modifycomm">수정하기</button>
					 			-->
					 		</c:otherwise>
					 	</c:choose>
						</div> <!-- end of modifyInput -->
					 </div> <!-- end of modifyBox -->
				</c:forEach>	
			</c:when>
				<c:otherwise>
					댓글이 없습니다.
				</c:otherwise>
		</c:choose>
			
		<hr>	
		<!-- 댓글 입력하기 -->
		<c:if test="${sessionScope.id != null}"> <!-- 0810샛별 -->
			<div class="commentInput">
				<form action="./commentInput" method="post" onsubmit="return check()">
		   		<textarea name="comment" id="comment1"></textarea>
		    	<input type="hidden" name="bno" value="${dto.bno }">
		    	<button type="submit" id="commentBtn">댓글쓰기</button>
		    	</form>
			</div> <!-- end of commentInput -->
		</c:if>
		<a href="./board">게시판으로</a>
		<div style="height: 200px"></div>
	</div> <!-- end of detail -->
</div> <!-- end of contentbox -->
</div> <!-- end of article -->
</body>
</html>