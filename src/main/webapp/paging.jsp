<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<fmt:parseNumber integerOnly="true" var="totalPage" value="${totalCount/PAGENUMBER}" scope="request"/>

<c:if test="${totalCount % PAGENUMBER ne 0 }">
	<c:set var="totalPage" value="${totalPage + 1 }" scope="request"/>
</c:if> <!-- 총 갯수/10의 나머지가 0이 아니면 1페이지 추가 -->

<c:if test="${page % PAGENUMBER ne 0 }">
	<fmt:parseNumber integerOnly="true" var="startPage" value="${page/PAGENUMBER }" scope="request"/>
	<c:set value="${startPage * PAGENUMBER + 1 }" var="startPage" scope="request"/>
</c:if> <!-- 11페이지가 넘어가면 startPage 재설정 -->

<c:if test="${page % PAGENUMBER eq 0 }">
	<c:set value="${page - (PAGENUMBER - 1) }" var="startPage" scope="request"/>
</c:if>

<c:set value="${startPage + 9 }" var="endPage" scope="request"/>

<!-- jstl 기호 참고) gt : >, lt : < -->
<c:if test="${startPage + 9 gt totalPage }">
	<c:set var="endPage" value="${totalPage }" scope="request"/>
</c:if> <!-- startPage + 9가 총 페이지보다 크다면 endPage 재설정 -->

<!-- 페이징 UI 구현 시작 -->
<!-- "처음", 11페이지부터 이전 버튼 보임 -->
<c:if test="${page ne 1 }">
	<a href="./${pageName}?page=1">처음</a>
</c:if>
<c:if test="${page gt PAGENUMBER}">
	<a href="./${pageName}?page=${page - 10 }">이전</a>
</c:if>
<!-- < 화살표(-1 페이지) -->
<c:if test="${page ne 1 }">
	<a href="./${pageName}?page=${page - 1}">◀</a>
</c:if>

<!-- 현재 페이지는 a에 링크 걸지 않았고 bold만 주었습니다. -->
<c:forEach begin="${startPage }" end="${endPage }" var="i">
	<c:if test="${i eq page }">
		<a style="font-weight: bold;">${i }</a>
	</c:if>
	<c:if test="${i ne page }">
		<a href="./${pageName}?page=${i }">${i }</a>
	</c:if>
</c:forEach>

<!-- > 화살표(+1 페이지) -->
<c:if test="${page ne totalPage }">
	<a href="./${pageName}?page=${page + 1}">▶</a>
</c:if>

<c:if test="${page lt totalPage - 9 }">
	<a href="./${pageName}?page=${page + 10}">다음</a>
</c:if>
<c:if test="${totalPage ne page }">
	<a href="./${pageName}?page=${totalPage}">끝</a>
</c:if>