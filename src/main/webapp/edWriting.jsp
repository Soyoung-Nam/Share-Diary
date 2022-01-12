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


<link rel="stylesheet" href="fonts.css">
<link rel="stylesheet" href="WritingIcons.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js">
</script> <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script> 
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet"> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>







<script>
$(document).ready(function() { 
	$('#summernote').summernote({
		tabsize: 2,
		width: 600,
		height:500,
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','기쁨밝음체','강부장님체', '고딕아니고 고딩체'],
		fontNamesIgnoreCheck: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','기쁨밝음체', '강부장님체', '고딕아니고 고딩체']
		}); 
}); 

</script>


<script type="text/javascript">
function btn0(){
	var edwrite = document.edWrite;
	var date = edwrite.date.value;
	var title =edwrite.title.value;
	var content = diaryWrite.content.value;
	if ((date.length ==0|| date == "")||(title.length==0||title =="")||(content.lrngth==0 || content=="")){
		alert("날짜, 제목, 내용은 필수로 입력하셔야 합니다");	
	} else{
		alert("교환일기는 마음대로 삭제할수없게 되어있습니다. 상대편에게 양해를 구해 주세요");
		edwrite.method = "post";
		edwrite.action = "EdDetail"; 
		edwrite.submit();
	}
}


</script>


<link rel="stylesheet" href="WritingIcons.css">

<title>Insert title here</title>
</head>
<body>


<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>


<div id = "contentBox" style="display:flex; flex-direction:row;">
	<div id ="leftContent" style= "width:500px; height: 100%; border: 1px solid violet; border-radius: 2em;">
		<div id="view" align="left" style="display:flex; flex-direction:column;">
			<div>
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
			</div>
		</div>
	</div>
	<div id = "rightContent" style= "width:600px; height: 100%; border: 1px solid skyblue; border-radius: 2em;">
		<form name = "edWrite" action ="ExchangediaryWriting" method="post" enctype="multipart/form-data">
					
			<input  type = date name="date" required="required">
			<br><div  style ="display:flex; flex-direction:row;" >
			<input type = "radio" name="weather" value = "sunny" id ="sunny"/>
			<label for "sunny" ><img src="icon/sunny.png" style ="cursor:pointer; height:40px; width:40px;"></label>
			<input type = "radio" name="weather" value = "cloudy" id ="cloudy" />
			<label for "cloudy" ><img src="icon/cloudy.png" style ="cursor:pointer; height:40px; width:40px;"></label>
			<input type = "radio" name="weather" value = "rainy" id ="rainy" />
			<label for "rainy" ><img src="icon/rainy.png" style ="cursor:pointer; height:40px; width:40px;"></label>
			<input type = "radio" name="weather" value = "snowy"id ="snowy"/>
			<label for "snowy" ><img src="icon/snowy.png" style ="cursor:pointer; height:40px; width:40px;"></label>
			<br></div>
					<input type="radio" name="feel" value="happy" id ="happy"/>
					<label for "happy" ><img src="icon/happy.png" style ="cursor:pointer; height:40px; width:40px;"></label>
					<input type="radio" name="feel" value="good"id ="good"/>
					<label for "good" ><img src="icon/good.png" style ="cursor:pointer; height:40px; width:40px;"></label>
					<input type="radio" name="feel" value="average" id ="average"/>
					<label for "average" ><img src="icon/average.png" style ="cursor:pointer; height:40px; width:40px;"></label>
					<input type="radio" name="feel" value="bad" id="bad"/>
					<label for "bad" ><img src="icon/bad.png" style ="cursor:pointer; height:40px; width:40px;"></label>
					<input type="radio" name="feel" value="worst"id ="worst"/>
					<label for "worst" ><img src="icon/worst.png" style ="cursor:pointer; height:40px; width:40px;"></label>
					<br>
			<input type= "text" name="title" required="required" value="오늘의 제목은?"/>
			<textarea id="summernote" name="content" required="required"> 여러가지 손글씨로 써보세요!</textarea>
			<button type = "submit" onclick ="javascript:btn0()">교환일기 확정</button>
		</form>
				
		
			
				
	</div>
</div>

</div>


</body>
</html>