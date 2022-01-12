<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Write</title>
<link href="./css/boardWrite.css" rel="stylesheet"> <!-- css파일 따로 만들었습니다! -->
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>

<div id="contentbox">
	<div id="writebox">
		<form action="./boardWrite" method="POST" enctype="multipart/form-data">
			<div id="titlebar"> <!-- 옆으로 나란히 예쁘게 놓고싶었는데 실패했습니다....-샛별 -->
				<select name="category" id="category"> <!-- 카테고리 드롭다운 -->
					<option value="daily" selected>일상</option>
					<option value="movie">영화</option>
					<option value="humor">유머</option>
				</select>
				
				<input type="text" name="btitle" id="btitle" required="required" placeholder="제목">
			</div>
			<textarea name="bcontent" id="bcontent" required="required" placeholder="내용을 입력하세요."></textarea> <!-- 0813 소영 -->
			<input type="file" name="file" accept=".gif, .png, .jpg, .jpeg, .bmp" id="selectfile"> <!-- 0813 소영 -->
			<button type="submit">글쓰기</button> <!-- 0813 소영 -->
		</form>
	</div><!-- end of writebox -->
</div><!-- end of contentbox -->
</div> <!-- end of article -->
</body>
</html>