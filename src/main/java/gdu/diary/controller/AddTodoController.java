package gdu.diary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.diary.service.TodoService;
import gdu.diary.vo.*;

@WebServlet("/auth/addTodo")
public class AddTodoController extends HttpServlet {
	
	private TodoService todoService;
	
		// todo 입력폼
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 수집
			int year = Integer.parseInt(request.getParameter("year"));
			int month = Integer.parseInt(request.getParameter("month"));
			int day = Integer.parseInt(request.getParameter("day"));		
			TodoDate todoDate = new TodoDate();
			todoDate.setYear(year);
			todoDate.setMonth(month);
			todoDate.setDay(day);
			// todoDate.toString(); // 디버깅
			
			request.setAttribute("todoDate", todoDate);
			request.getRequestDispatcher("/WEB-INF/view/addTodo.jsp").forward(request, response);	
		}
		
		// todo 액션
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 수집
			HttpSession session = request.getSession();
			int memberNo = ((Member)(session.getAttribute("sessionMember"))).getMemberNo();
			String todoDate = request.getParameter("todoDate");
			String todoTitle = request.getParameter("todoTitle");
			String todoContent = request.getParameter("todoContent");
			String todoFontColor = request.getParameter("todoFontColor");
			
			Todo todo = new Todo();
			todo.setMemberNo(memberNo);
			todo.setTodoDate(todoDate);
			todo.setTodoTitle(todoTitle);
			todo.setTodoContent(todoContent);
			todo.setTodoFontColor(todoFontColor);
			
			//  서비스 호출
			this.todoService = new TodoService();
			this.todoService.addTodo(todo);
			String[] arr = todoDate.split("-");
			response.sendRedirect(request.getContextPath()+"/auth/diary?targetYear="+arr[0]+"&targetMonth="+(Integer.parseInt(arr[1])-1));
		}
}
