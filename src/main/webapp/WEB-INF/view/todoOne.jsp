<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>todoOne</title>
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

		<table class="table">	
			<tr>
				<td><div class="form-group">todo_date: </div></td>
				<td><div class="form-group">${todo.todoDate}</div></td>
			</tr>
			<tr>
				<td><div class="form-group">todo_title: </div></td>
				<td><div class="form-group">${todo.todoTitle}</div></td>
			</tr>
			<tr>
				<td><div class="form-group">todo_content: </div></td>
				<td><div class="form-group">${todo.todoContent}</div></td>
			</tr>
			<tr>
				<td><div class="form-group">todo_font_color: </div></td>
				<td><div class="form-group"><input type="color" name="todoFontColor" value="${todo.todoFontColor}"></div></td>
			</tr>
			<tr>
				<td>
					<div>
						<a href="${pageContext.request.contextPath}/auth/modifyTodo?todoNo=${todo.todoNo}">
							<button type="button" class="btn btn-info">일정 수정</button>
						</a>
						<a href="${pageContext.request.contextPath}/auth/deleteTodo?todoNo=${todo.todoNo}">
							<button type="button" class="btn btn-info">일정 삭제</button>
						</a>
						<a href="${pageContext.request.contextPath}/auth/diary">
							<button type="button" class="btn btn-info">뒤로</button>
						</a>
					</div>
				</td>
				<td></td>
			</tr>
		</table>

	</div>
</body>
</html>