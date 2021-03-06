<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>diary</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
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
		<br><br>
		<h1>D-DAY List</h1>
		<div>
			<table class="table">
				<tr>
					<th>todoDate</th>
					<th>todoTitle</th>
					<th>dday</th>
				</tr>
				<c:forEach var="m" items="${diaryMap.ddayList}">
					<tr>
						<td>${m.todoDate}</td>
						<td>
							<a href="${pageContext.request.contextPath}/auth/todoOne?todoNo=${m.todoNo}">
								${m.todoTitle}
							</a>
						</td>
						<td>D-${m.dday}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br>
		
		<!-- 달력의 전체 칸 수 -->
		<c:set var="totalCell" value="${diaryMap.startBlank + diaryMap.endDay + diaryMap.endBlank}"></c:set>

		<table class="table" id="calendar">
		    <caption>
			    <a href="${pageContext.request.contextPath}/auth/diary?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth-1}">이전달</a>
			    ${diaryMap.targetYear}년 ${diaryMap.targetMonth+1}월
			    <a href="${pageContext.request.contextPath}/auth/diary?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth+1}">다음달</a>
		    </caption>
			<tr class="weekdays">
			  <th scope="col">Sunday</th>
			  <th scope="col">Monday</th>
			  <th scope="col">Tuesday</th>
			  <th scope="col">Wednesday</th>
			  <th scope="col">Thursday</th>
			  <th scope="col">Friday</th>
			  <th scope="col">Saturday</th>
			</tr>
			
			<tr class="days">
				<c:forEach var="i" begin="1" end="${totalCell}" step="1">
					<!-- 해당 달 앞의 공백 출력하기 -->
					<c:if test="${(i - diaryMap.startBlank) < 1}">
						<td class="day"></td>
					</c:if>
					<!-- 달의 날짜 출력하기 -->
					<c:if test="${(i - diaryMap.startBlank) > 0 && (i - diaryMap.startBlank) <= diaryMap.endDay}">
						<td class="day">
							<div class="form-group">
								<a href="${pageContext.request.contextPath}/auth/addTodo?year=${diaryMap.targetYear}&month=${diaryMap.targetMonth}&day=${i - diaryMap.startBlank}">
									<span style="font-size: 130%">${i - diaryMap.startBlank}</span>
								</a>
								<!-- 휴일 출력하기 -->
								<c:forEach var="holidayList" items="${holidayList}">
									<c:if test="${holidayList.locdate == (i - diaryMap.startBlank)}">
										<div>
											<span style="color:red">${holidayList.dateName}</span>
										</div>
									</c:if>
								</c:forEach>
							</div>
							
							<!-- 일정 출력하기 -->
							<div class="form-group">
								<c:forEach var="todo" items="${diaryMap.todoList}">
									<c:if test="${todo.todoDate == (i - diaryMap.startBlank)}">
										<div class="form-group">
											<a href="${pageContext.request.contextPath}/auth/todoOne?todoNo=${todo.todoNo}"><span style="color:${todo.todoFontColor}">${todo.todoTitle}...</span></a>
										</div>	
									</c:if>
								</c:forEach>
							</div>
							
						</td>
					</c:if>
					<!-- 해당 달 뒤의 공백 출력하기 -->
					<c:if test="${i - diaryMap.startBlank > diaryMap.endDay}">
						<td class="day"></td>
					</c:if>
					<!-- 한주가 끝난 뒤 칸 넘기기 -->
					<c:if test="${i%7 == 0}">
						</tr><tr class="days">
					</c:if>
				</c:forEach>
			</tr>
		</table>	
	</div>
</body>
</html>