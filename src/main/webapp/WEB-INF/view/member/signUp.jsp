<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signUp</title>
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
	
	<div class="container">
		<form method="post" action="${pageContext.request.contextPath}/signUp">
			<table class="table">	
			<tr>
				<td><div class="form-group">memberId: </div></td>
				<td><input type="email" name="memberId" required="required"></td>
			</tr>
			<tr>
				<td><div class="form-group">memberPw: </div></td>
				<td><input type="password" name="memberPw" required="required"></td>
			</tr>	
			<tr>
				<td>
					<div>
						<button type="submit" class="btn btn-info">회원가입</button>
						<a href="${pageContext.request.contextPath}/login">
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