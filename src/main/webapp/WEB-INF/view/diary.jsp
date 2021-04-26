<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>diary</title>
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
					<!-- 해당 달 앞의 공백 출력하기 -->
					<c:if test="${(i - diaryMap.startBlank) < 1}">
						<td></td>
					</c:if>
					<!-- 달의 날짜 출력하기 -->
					<c:if test="${(i - diaryMap.startBlank) > 0 && (i - diaryMap.startBlank) <= diaryMap.endDay}">
						<td>
							<div>
								<a href="${pageContext.request.contextPath}/auth/addTodo?year=${diaryMap.targetYear}&month=${diaryMap.targetMonth}&day=${i - diaryMap.startBlank}">
									${i - diaryMap.startBlank}
								</a>
							</div>
							
							<div>
								<c:forEach var="todo" items="${diaryMap.todoList}">
									<c:if test="${todo.todoDate == (i - diaryMap.startBlank)}">
										<div>
											<a href="${pageContext.request.contextPath}/auth/todoOne?todoNo=${todo.todoNo}">${todo.todoTitle}...</a>
										</div>	
									</c:if>
								</c:forEach>
							</div>
							
						</td>
					</c:if>
					<!-- 해당 달 뒤의 공백 출력하기 -->
					<c:if test="${i - diaryMap.startBlank > diaryMap.endDay}">
						<td></td>
					</c:if>
					<!-- 한주가 끝난 뒤 칸 넘기기 -->
					<c:if test="${i%7 == 0}">
						</tr><tr>
					</c:if>
				</c:forEach>
			</tr>
		</table>
	</div>
</body>
</html>