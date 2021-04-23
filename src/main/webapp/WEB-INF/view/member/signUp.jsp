<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signUp</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/signUp">
		<div>
			memberId :
		</div>
		<div>
			<input type="email" name="memberId" required="required">
		</div>
		<div>
			memberPw :
		</div>
		<div>
			<input type="password" name="memberPw" required="required">
		</div>
		<div>
			<button type="submit">입력</button>
		</div>
	</form>
</body>
</html>