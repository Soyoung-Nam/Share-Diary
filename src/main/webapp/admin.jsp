<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${list eq null }">
	<c:redirect url="./admin"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="./css/admin.css" rel="stylesheet">
<script type="text/javascript">
function select(){
	var ip = document.getElementById("ip").value;
	var target = document.getElementById("target").value;
	location.href='admin?ip='+ip+'&target='+target;
}
</script>
</head>
<body>
<div id="banner"><c:import url="banner.jsp"/></div>

<div id="article">

<div id="menu"><c:import url="menu.jsp"/></div>

<div id="contentbox">
	<div id="adminbox">
				
				<c:choose>
					<c:when test="${fn:length(list) > 0 }">
				<table style="width: 100%; font-size: smaller;">
					<tr class="th">
						<th>no</th>
						<th>
						<select onchange="select()" id="ip">
							<option value="">ip를 선택</option>
							<c:forEach items="${ipList }" var="i">
								<c:if test="${i eq ip }">
									<option value="${i }" selected="selected">${i }</option>
								</c:if>
								<c:if test="${i ne ip }">
									<option value="${i }">${i }</option>
								</c:if>
							</c:forEach>
						</select>
						</th>
						<th>date</th>
						<th>
						<select onchange="select()" id="target">
							<option value="">target을 선택</option>
							<c:forEach items="${targetList }" var="t">
								<c:if test="${target eq t }">
									<option value="${t }" selected="selected">${t }</option>
								</c:if>
								<c:if test="${target ne t }">
									<option value="${t }">${t }</option>
								</c:if>
							</c:forEach>
						</select>
						</th>
						<th>etc</th>
					</tr>
					<c:forEach items="${list }" var="l">
					<tr>						
						<td>${l.log_no }</td>
						<td>${l.log_ip }</td>
						<td>${l.log_date }</td>
						<td>${l.log_target }</td>
						<td><c:out value="${fn:substring(l.log_etc, 0, 40) }"/> </td>
					</tr>
					</c:forEach>
				</table>
					<div id="paging">
						<c:set var="pageName" value="admin" scope="request"/>
						<c:set var="PAGENUMBER" value="10" scope="request"/>
						<c:import url="paging.jsp"/>
					</div>
					</c:when>
					<c:otherwise>
						<h2>출력할 데이터가 없습니다</h2>
					</c:otherwise>
				</c:choose>
				
				<!-- 검색박스 -->
				<form action="admin" method="post" id="searchboard">
					<select name="searchname">
						<option value="ip">ip</option>
						<option value="target">target</option>
						<option value="etc">etc</option>
					</select>
					<input type="text" id="searchtext" name="search">
					<button type="submit" id="submitBtn">검색</button>
				</form>
				
			</div><!-- end of adminbox -->
		</div><!-- end of contentbox -->
	</div> <!-- end of article -->
</body>
</html>