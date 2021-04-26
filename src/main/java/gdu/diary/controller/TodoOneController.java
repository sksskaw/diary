package gdu.diary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.TodoService;
import gdu.diary.vo.Todo;

@WebServlet("/auth/todoOne")
public class TodoOneController extends HttpServlet {

	TodoService todoService = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		
		todoService = new TodoService();
		Todo todo = new Todo();
		todo = todoService.getTodoOne(todoNo);
		
		request.setAttribute("todo", todo);
		request.getRequestDispatcher("/WEB-INF/view/todoOne.jsp").forward(request, response);	
	}

}