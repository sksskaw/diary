<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addTodo</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<!-- 네비게이션 바 -->
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
	
	<!-- main content -->
	<div class="container">
		<h1>일정 입력</h1>
		
		<form action="${pageContext.request.contextPath}/auth/addTodo" method="post">
			<table class="table">	
				<tr>	
					<td><div class="form-group">todoDate: </div></td>
					<td><input type="text" name="todoDate" value="${todoDate.toString()}" readonly="readonly"></td>
				</tr>
				<tr>
					<td><div class="form-group">todoTitle: </div></td>
					<td><input type="text" name="todoTitle" required="required"></td>
				</tr>	
				<tr>	
					<td><div class="form-group">todoContent: </div></td>
					<td><textarea name="todoContent" cols="80" rows="5" required="required"></textarea></td>
				</tr>
				<tr>
					<td><div class="form-group">todoFontColor: </div></td>
					<td><input type="color" name="todoFontColor"></td>
				</tr>		
				<tr>
					<td>
						<div>
							<button type="submit" class="btn btn-info">일정 추가</button>
							<a href="${pageContext.request.contextPath}/auth/diary">
								<button type="button" class="btn btn-info">취소</button>
							</a>
						</div>
					</td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>