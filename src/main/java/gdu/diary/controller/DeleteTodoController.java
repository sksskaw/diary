package gdu.diary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.TodoService;

@WebServlet("/auth/deleteTodo")
public class DeleteTodoController extends HttpServlet {
	
	TodoService todoService = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		
		todoService = new TodoService();
		int result = todoService.RemoveTodo(todoNo);
		if(result == 0) {
			System.out.println("삭제 오류");
			response.sendRedirect(request.getContextPath()+"/auth/modifyTodo?todoNo=" + todoNo);
			return;
		}
	
		response.sendRedirect(request.getContextPath()+"/auth/diary");
	}
}