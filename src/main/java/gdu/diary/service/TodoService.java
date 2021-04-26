package gdu.diary.service;

import java.sql.*;
import java.util.Map;

import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Todo;

public class TodoService {
	private DBUtil dbutil;
	public TodoDao todoDao;
	
	// 일정 추가 메소드
	public int addTodo(Todo todo) {
		dbutil = new DBUtil();
		todoDao = new TodoDao();
		Connection conn = null;
		int rowCnt = 0;
		try {
			conn = this.dbutil.getConnection();
			rowCnt = this.todoDao.insertTodo(conn, todo);
			conn.commit();
		} catch(SQLException e){
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, null, conn);
		}
		
		return rowCnt;
	}
	
	// 일정 새부 정보 가져오기
	public Todo getTodoOne(int todoNo){
		dbutil = new DBUtil();
		todoDao = new TodoDao();
		Todo returnTodo = new Todo();
		Connection conn = null;
		
		try {
			conn = this.dbutil.getConnection();
			returnTodo = this.todoDao.selectTodoOne(conn, todoNo);
			conn.commit();
		} catch(SQLException e){
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, null, conn);
		}
		return returnTodo;
	}
	
	// 일정 수정 메소드
	public int modifyTodo(Todo todo){
		dbutil = new DBUtil();
		todoDao = new TodoDao();
		int returnResult = 0;
		Connection conn = null;
		
		try {
			conn = this.dbutil.getConnection();
			returnResult = this.todoDao.updateTodo(conn, todo);
			conn.commit();
		} catch(SQLException e){
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, null, conn);
		}
		
		return returnResult;
	}
	
	public int RemoveTodo(int todoNo){
		dbutil = new DBUtil();
		todoDao = new TodoDao();
		int returnResult = 0;
		Connection conn = null;
		
		try {
			conn = this.dbutil.getConnection();
			returnResult = this.todoDao.deleteTodoOne(conn, todoNo);
			conn.commit();
		} catch(SQLException e){
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, null, conn);
		}
		
		return returnResult;
	}
}
