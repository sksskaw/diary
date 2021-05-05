package gdu.diary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.TodoService;
import gdu.diary.vo.Todo;

@WebServlet("/auth/modifyTodo")
public class ModifyTodoController extends HttpServlet {

	TodoService todoService = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		
		todoService = new TodoService();
		Todo todo = new Todo();
		
		if(todoService.getTodoOne(todoNo).size() != 0) {
			todo = todoService.getTodoOne(todoNo).get(0);
		}
		
		request.setAttribute("todo", todo);
		request.getRequestDispatcher("/WEB-INF/view/modifyTodo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String todoTitle = request.getParameter("todoTitle");
		String todoContent = request.getParameter("todoContent");
		String todoFontColor = request.getParameter("todoFontColor");
		
		todoService = new TodoService();
		Todo todo = new Todo();
		todo.setTodoNo(todoNo);
		todo.setTodoTitle(todoTitle);
		todo.setTodoContent(todoContent);
		todo.setTodoFontColor(todoFontColor);
		
		int result = todoService.modifyTodo(todo);
		if(result == 0) {
			System.out.println("수정 오류");
			response.sendRedirect(request.getContextPath()+"/auth/modifyTodo?todoNo=" + todoNo);
			return;
		}
		
		if(todoService.getTodoOne(todoNo).size() != 0) {
			todo = todoService.getTodoOne(todoNo).get(0);
		}
		
		request.setAttribute("todo", todo);
		request.getRequestDispatcher("/WEB-INF/view/todoOne.jsp").forward(request, response);
	}

}