<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="fonts/fonts.css">
<link rel="stylesheet" href="WritingIcons.css">
<link rel="stylesheet" href="menu.css">

<title></title>



<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js">
</script> <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script> 
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet"> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>

		
<script>
	function btn(){
		var date = diaryWrite.date.value;
		var title =diaryWrite.title.value;
		var content = diaryWrite.content.value;
		if ((date.length ==0|| date == "")||(title.length==0||title =="")||(content.lrngth==0 || content=="")){
			alert("날짜, 제목, 내용은 필수로 입력하셔야 합니다");	
		} else{
			alert("항목을 공개로 설정하셨다면 공유일기장에 올라갑니다. 다른 사람들이 열람할수있습니다! ");
			
			diaryWrite.method = "post";
			diaryWrite.action ="DiaryWriting";
			diaryWrite.submit();
		}
	}
</script>
			
	
<script> $(document).ready(function() { 
	$('#summernote').summernote({
		placeholder:"여러가지 손글씨로 써보세요!",
		tabsize: 2,
		width: 1000,
		height:500,
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','기쁨밝음체','강부장님체', '고딕아니고 고딩체'],
		fontNamesIgnoreCheck: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','기쁨밝음체', '강부장님체', '고딕아니고 고딩체']
		}); 
}); 
</script>

</head>
<body>


<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>
	
		<div id = 'contentsBox'>
				<form name = diaryWrite action="./DiaryWriting" method="post" enctype="multipart/form-data">
					
					<input type= "radio" name="allow" value="open">공개
					<input type= "radio" name="allow" value="close">비공개
					
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
					<input type= "text" name="title" required="required" value="오늘의 제목은?">
					<textarea id="summernote" name="content" required="required"></textarea>
				</form>
				
				<button type="submit" onClick="javascript:btn()">오늘의 일기 끝!</button>
				<button onClick= "location.href='diaryView.jsp'">목록으로</button>
				
				
		</div>
		
		
</div>

			
</body>
</html>