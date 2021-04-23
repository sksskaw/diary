<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>diary</title>
</head>
<body>
	<c:set var="totalCell" value="${diaryMap.startBlank + diaryMap.endDay + diaryMap.endBlank}"></c:set>
	<h3>${diaryMap.targetYear}년 ${diaryMap.targetMonth+1}월</h3>
	<a href="${pageContext.request.contextPath}/auth/diary?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth-1}">이전달</a>
	<a href="${pageContext.request.contextPath}/auth/diary?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth+1}">다음달</a>
	<table border="1" width="90%">
		<tr>
			<td>일</td>
			<td>월</td>
			<td>화</td>
			<td>수</td>
			<td>목</td>
			<td>금</td>
			<td>토</td>
		</tr>
		<tr>
			<c:forEach var="i" begin="1" end="${totalCell}" step="1">
					
				<c:if test="${(i - diaryMap.startBlank) < 1}">
					<td></td>
				</c:if>
					
				<c:if test="${(i - diaryMap.startBlank) > 0 && (i - diaryMap.startBlank) <= diaryMap.endDay}">
					<td>${i - diaryMap.startBlank}</td>
				</c:if>
				
				<c:if test="${i - diaryMap.startBlank > diaryMap.endDay}">
					<td></td>
				</c:if>
				
				<c:if test="${i%7 == 0}">
					</tr><tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
</body>
</html>