<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<!-- 로그인 전 -->
	<c:if test="${sessionMember == null}">
		<nav>
			<input type="checkbox" id="check">
			<label for="check" class="checkbtn">
				<i class="fas fa-bars"></i>
			</label>
			<label class="logo">Diary</label>
			<ul>
				<li><a class="active" href="${pageContext.request.contextPath}/login">Home</a></li>
			</ul>
		</nav>
		
		<!-- main content -->
		<div class="container">
			<h1>login</h1>
			<form method="post" action="${pageContext.request.contextPath}/login">
				<table class="table">
					<tr>
						<td><div class="form-group">ID: &nbsp;
							<input type="text" name="memberId" required="required"></div></td>
					</tr>
					<tr>
						<td><div class="form-group">PW: 
						<input type="password" name="memberPw" required="required"></div></td>
					</tr>
					
					<tr>
						<td>
							<div>
								<button type="submit" class="btn btn-info">로그인</button>
								<a href="${pageContext.request.contextPath}/signUp">
									<button type="button" class="btn btn-info">회원가입</button>
								</a>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</c:if>

	<!-- 로그인 후 -->
	<c:if test="${sessionMember != null}">
		<nav>
			<input type="checkbox" id="check">
		      <label for="check" class="checkbtn">
		        <i class="fas fa-bars"></i>
		      </label>
		      <label class="logo">Diary</label>
		      <ul>
		      	 <li><a class="active" href="${pageContext.request.contextPath}/auth/diary">Home</a></li>
		      	 <li><a href="${pageContext.request.contextPath}/auth/logout">로그아웃</a></li>
		      	 <li><a href="${pageContext.request.contextPath}/auth/modifyMember">정보수정</a></li>
		      	 <li><a href="${pageContext.request.contextPath}/auth/removeMenber">회원 탈퇴</a></li>
		      	 <li><a href="${pageContext.request.contextPath}/auth/diary">다이어리</a></li>
		      </ul>
		</nav>
	</c:if>
</body>
</html>