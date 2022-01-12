<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${id eq null }">
	<c:redirect url="./login.jsp"/>
</c:if>


<c:if test="${dto eq null }">
	<!-- dto가 없다면 서블릿으로 던지기 -->
	<c:redirect url="./diaryView"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="menu.css">


<script type="text/javascript"> 
	function exportAlert(){ 
		var export0 = document.export0;
		alert('맨 처음의 일기부터 마지막 일기까지 텍스트 파일로 내보냅니다.'); 
		export0.method = "post";
		export0.action ="Export";
		export0.submit();
}
</script>


<script type="text/javascript">
	function selectionView(){
		var dateSelection = document.dateSelection;
		var dateStart = dateSelection.dateStart.value;
		var dateEnd =   dateSelection.dateEnd.value;
		if ((dateStart.length == 0||dateStart== "")||(dateEnd.length==0||dateEnd == "")){
			alert('시작과 끝날짜 모두 입력해 주세요');	
		} else{
			dateSelection.method = "post";
			dateSelection.action = "DateSelectionView";
			dateSelection.submit();
		}
	}
</script>




<title></title>

</head>
<body>

<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>
<div id = "cotentbox">
<div style="display:flex; flex-direction:row;">
<form name="dateSelection" >
	시작 날짜:<input  type = date name="dateStart"/>
	끝 날짜:<input  type = date name="dateEnd"/>
	<input type="button" onclick="selectionView()" value ="일기 찾기"/>
</form>


<form name= "export0" >
<input type= "button" onclick= "exportAlert()" value ="일기 내보내기"/>
</form></div>
<div><button onclick="location.href='diaryWriting.jsp'">일기 쓰기</button></div>

<c:forEach items="${dto}" var="dto">
		<br>
		<a href="./detail?dNo=${dto.no }">
		<div align="center" style="width:100%; height: 100px; background: linear-gradient(to bottom right,#e6e3e0, #9e90c4); color:white; border-radius: 0.5em;">
		<div>${dto.title }</div>
		<div>날짜: ${dto.date }</div>
		<div>이 날의 기분: ${dto.feel }</div>
		</div>
		
		</a>
</c:forEach>


</div>
</div>
</body>
</html>