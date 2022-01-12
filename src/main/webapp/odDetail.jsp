<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="menu.css">



<script type="text/javascript">
<!-- 댓글쓰기 -->



$(document).ready(function() {
	//수정하기 기능
	$("button[name='modifycomm']").click(function() {
		var bcontent = $(".modifyInput").children(".bcontent").text();
		var bno = $(".modifyInput").children(".bno").text();
		var bcno = $(".modifyInput").children(".bcno").text();
		$(".modifyInput").parent().html(
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
</script>



<title>Insert title here</title>
</head>
<body>

<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>


		<div id = "contentBox" align="left"  style = "width:600px; min-height: 500px; background: linear-gradient(to bottom right,#e6e3e0, #9e90c4); color:white; border-radius: 0.5em; ">
			<table>
				<tr>
					<th>제목</th>
					<td>${dto.title }</td>
				</tr>
				<tr>
					<th>쓴날짜</th>
					<td>${dto.date }</td>
				</tr>
				<tr>
					<th>날씨</th>
					<td>${dto.weather }</td>
				</tr>
				<tr>
					<th>기분</th>
					<td>${dto.feel }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${dto.content }
						<br>
						<c:if test="${dto.file ne null }">						
						<img alt="그림" src="./imgs/${dto.file }" width="100%"> 
						</c:if>
					</td>
				</tr>
			</table>
		
		
			
		<c:if test="${dto.commentcount > 0}">
		${dto.commentcount }개의 댓글이 있습니다.
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
					 		${i.bcdate } / <button onclick="likeUp(${i.bcno }, ${i.bno })">${i.bclike }</button>
					 	</div> <!-- end of commentdate -->
					 </div> <!-- end of commentinfo -->
					 	<div class="modifyBox">
					 	<div class="modifyInput">
					 	<div class="bcontent">${i.bccontent }</div>
					 	<div class="bno" style="display: none;">${i.bno }</div>
					 	<div class="bcno" style="display: none;">${i.bcno }</div>
					 
					 	<c:choose>
					 		<c:when test="${i.id eq sessionScope.id }">
					 			<button onclick="commentDelete(${i.bcno }, ${i.bno })">삭제하기</button>
					 			<button name="modifycomm">수정하기</button>
							</c:when>
					 		<c:otherwise>
					 			<!-- 로그인 세션 되면 삭제하기! -->
					 			<button onclick="commentDelete(${i.bcno }, ${i.bno })">삭제하기</button>
					 			<button name="modifycomm">수정하기</button>
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
			
			
			
		<!-- 댓글 입력하기 -->
		<div class="commentInput">
			<form action="./commentInput" method="post" onsubmit="return check()">
	   		<textarea name="comment" id="comment1"></textarea>
	    	<input type="hidden" name="bno" value="${dto.bno }">
	    	<button type="submit" id="commentBtn">댓글쓰기</button>
	    	</form>
		</div> <!-- end of commentInput -->
		
		
		
		
		</div>
	</div>

		
		
		
		
		
		
		
		
		
		
		
		
</body>
</html>