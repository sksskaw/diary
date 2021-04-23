<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<!-- 로그인 전 -->
	<c:if test="${sessionMember == null}">
		<h1>login</h1>
		<form method="post" action="${pageContext.request.contextPath}/login">
			<div>
				<div>ID : </div>
				<div><input type="text" name="memberId"></div>
				<div>PW : </div>
				<div><input type="password" name="memberPw"></div>
				<div>
					<button type="submit">로그인</button>
					<a href="${pageContext.request.contextPath}/signUp"><button type="button">회원가입</button></a>
				</div>
			</div>
		</form>
		
	</c:if>
	
	<!-- 로그인 후 -->
	<c:if test="${sessionMember != null}">
		<div>${sessionMember.memberId} 님 반갑습니다.</div>
		<div><a href="${pageContext.request.contextPath}/auth/logout">로그아웃</a></div>
		<div><a href="${pageContext.request.contextPath}/auth/modifyMember">정보수정</a></div>
		<div><a href="${pageContext.request.contextPath}/auth/removeMenber">탈퇴</a></div>
		<div><a href="${pageContext.request.contextPath}/auth/diary">다이어리</a></div>
	</c:if>
</body>
</html>