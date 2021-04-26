<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyTodo</title>
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
		<form method="post" action="${pageContext.request.contextPath}/auth/modifyTodo">
			<input type="hidden" name="todoNo" value="${todo.todoNo}">
			<table class="table">	
				<tr>
					<td><div class="form-group">todo_date: </div></td>
					<td><input type="text" name="todoDate" readonly="readonly" value="${todo.todoDate}"></td>
				</tr>
				<tr>
					<td><div class="form-group">todo_title: </div></td>
					<td><input type="text" name="todoTitle" required="required" value="${todo.todoTitle}"></td>
				</tr>
				<tr>
					<td><div class="form-group">todo_content: </div></td>
					<td><textarea name="todoContent" cols="80" rows="5" required="required">${todo.todoContent}</textarea></td>
				</tr>
				<tr>
					<td><div class="form-group">todo_font_color: </div></td>
					<td><div class="form-group"><input type="color" name="todoFontColor" value="${todo.todoFontColor}"></div></td>
				</tr>
				<tr>
					<td>
						<div>
							<button type="submit" class="btn btn-info">일정 수정</button>
							<a href="${pageContext.request.contextPath}/auth/todoOne?todoNo=${todo.todoNo}">
								<button type="button" class="btn btn-info">뒤로</button>
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