package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.*;

public class TodoDao {
	private DBUtil dbUtil;
	
	// 해당 달에 있는 모든 일정 가져오기
	public List<Todo> selectTodoListByDate(Connection conn, int memberNo, int targetYear, int targetMonth) throws SQLException {
		List<Todo> list = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			/*
			 * SELECT todo_no todoNo, LEFT(todo_title, 10) todoTitle FROM todo 
			 * WHERE member_no=? AND YEAR(todo_date)=? AND MONTH(todo_date)=?
			 */
			stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_LIST_BY_DATE);
			stmt.setInt(1, memberNo);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			System.out.println("selectTodoListByDate " + stmt);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Todo todo = new Todo();
				todo.setTodoNo(rs.getInt("todoNo"));
				todo.setTodoDate(rs.getString("todoDate"));
				todo.setTodoTitle(rs.getString("todoTitle"));
				todo.setTodoFontColor(rs.getString("todoFontColor"));
				list.add(todo);
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			stmt.close();
		}
		return list;
	}
	
	// 일정 추가
	public int insertTodo(Connection conn, Todo todo) throws SQLException {
		int rowCnt = 0;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.INSERT_TODO);
			stmt.setInt(1, todo.getMemberNo());
			stmt.setString(2, todo.getTodoDate());
			stmt.setString(3, todo.getTodoTitle());
			stmt.setString(4, todo.getTodoContent());
			stmt.setString(5, todo.getTodoFontColor());
			System.out.println("insertTodo " + stmt);
			rowCnt = stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		return rowCnt;
	}
	
	// 일정 삭제(회원탈퇴 시 해당 계정이 작성한 모든 일정 삭제)
	public int deleteTodoByMember(Connection conn, int memberNo) throws SQLException{
		this.dbUtil = new DBUtil();
		int returnResult = 0;
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_BY_MEMBER);
			stmt.setInt(1, memberNo);
			System.out.println("deleteTodoByMember " + stmt);
			returnResult = stmt.executeUpdate();
		} finally {
			this.dbUtil.close(null, stmt, null);
		}
		
		//System.out.println("삭제된 일정 개수" + returnResult);
		return returnResult;
	}
	
	// 일정 상세정보 가져오기
	public Todo selectTodoOne(Connection conn, int todoNo) throws SQLException {
		Todo returnTodo = new Todo();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_ONE_BY_KEY);
			stmt.setInt(1, todoNo);
			System.out.println("selectTodoOne " + stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnTodo.setTodoNo(rs.getInt("todo_no"));
				returnTodo.setTodoDate(rs.getString("todo_date"));
				returnTodo.setTodoTitle(rs.getString("todo_title"));
				returnTodo.setTodoContent(rs.getString("todo_content"));
				returnTodo.setTodoFontColor(rs.getString("todo_font_color"));
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			stmt.close();
		}
		return returnTodo;
	}
	
	// 일정 수정 메소드
	public int updateTodo(Connection conn, Todo todo) throws SQLException{
		this.dbUtil = new DBUtil();
		int returnResult = 0;
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(TodoQuery.UPDATE_TODO_ONE_BY_KEY);
			stmt.setString(1, todo.getTodoTitle());
			stmt.setString(2, todo.getTodoContent());
			stmt.setString(3, todo.getTodoFontColor());
			stmt.setInt(4, todo.getTodoNo());
			System.out.println("UpdateTodo " + stmt);
			returnResult = stmt.executeUpdate();
		} finally {
			this.dbUtil.close(null, stmt, null);
		}
		
		return returnResult;
	}
	
	// 일정 삭제 메소드
	public int deleteTodoOne(Connection conn, int todoNo) throws SQLException{
		this.dbUtil = new DBUtil();
		int returnResult = 0;
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_ONE_BY_MEMBER);
			stmt.setInt(1, todoNo);
			System.out.println("deleteTodoOne " + stmt);
			returnResult = stmt.executeUpdate();
		} finally {
			this.dbUtil.close(null, stmt, null);
		}
		
		return returnResult;
	}
	
	// d-day 리스트 가져오기
	public List<Map<String, Object>> selectTodoDdayList(Connection conn, int memberNo) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_DDAY_LIST);
			stmt.setInt(1, memberNo);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("todoNo", rs.getInt("todoNo"));
				map.put("todoDate", rs.getString("todoDate"));
				map.put("todoTitle", rs.getString("todoTitle"));
				map.put("dday", rs.getInt("dday"));
				list.add(map);
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			stmt.close();
		}
		return list;
	}
}
