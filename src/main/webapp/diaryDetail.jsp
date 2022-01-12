<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="menu.css">

<script type="text/javascript">
	function delete0(){
		if(confirm("이 일기를 잊어버리시겠습니까?")== true){
			location.href = "DiaryDelete?no=${dto.no}";
		}
	}
</script>


<title>Insert title here</title>
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>

		<div id = "contentbox" style="display:flex; flex-direction:column;">
		<div  style = "width:600px; height: 100%; background: linear-gradient(to bottom right,#e6e3e0, #9e90c4); color:white; border-radius: 0.5em; ">
			<table>
				<tr>
					<th>제목:</th>
					<td>${dto.title }</td>
				</tr>
				<tr>
					<th>날짜:</th>
					<td>${dto.date }</td>
				</tr>
				<tr>
					<th>날씨:</th>
					<td>${dto.weather }</td>
				</tr>
				<tr>
					<th>기분:</th>
					<td>${dto.feel }</td>
				</tr>
				<tr>
					<th>내용:</th>
					<td>${dto.content }
						<br>
						<c:if test="${dto.file ne null }">						
						<img alt="그림" src="./imgs/${dto.file }" width="100%"> 
						</c:if>
					</td>
				</tr>
			</table>
		</div>
			
		<form name="detailDelete">
		<input type="button" onclick = "delete0()" value = "일기 잊어버리기"/>
		
		</form>
			
		<button onclick = "location.href='diaryView.jsp'"style = "width :80px;">목록으로</button>
		</div>	
			
	</div>
</div>
</body>
</html>