package gdu.diary.service;

import java.sql.*;

import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Todo;

public class TodoService {
	private DBUtil dbutil;
	public TodoDao todoDao;
	
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
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, null, conn);
		}
		
		return rowCnt;
		
	}
}
