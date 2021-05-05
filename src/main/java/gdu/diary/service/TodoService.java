package gdu.diary.service;

import java.util.List;

import gdu.diary.dao.TodoDao;
import gdu.diary.vo.Todo;

public class TodoService {
	public TodoDao todoDao;
	
	// 일정 추가 메소드 Create
	public int addTodo(Todo todo) {
		this.todoDao = new TodoDao();
		return this.todoDao.insertTodo(todo);
	}
	
	// 일정 새부 정보 가져오기 Read
	public List<Todo> getTodoOne(int todoNo){
		this.todoDao = new TodoDao();
		return this.todoDao.selectTodoOne(todoNo);
	}
	
	// 일정 수정 메소드 Update
	public int modifyTodo(Todo todo){
		this.todoDao = new TodoDao();
		return this.todoDao.updateTodo(todo);
	}
	
	// 일정 삭제 메소드 Delete
	public int RemoveTodo(int todoNo){
		this.todoDao = new TodoDao();
		return this.todoDao.deleteTodoOne(todoNo);
	}
}
