<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error ${code }</title>
<link href="./css/error.css" rel="stylesheet">
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>
<div id="article">
<div id="menu"><c:import url="menu.jsp"/></div>

<div id="contentbox">
	<div id="errorbox">
		<h2>${code } 로 인하여 에러가 발생!</h2><br>
		<p>관리자에게 문의하세요. </p>
	</div>
</div> <!-- end of contentbox -->

</div> <!-- end of article -->
</body>
</html>