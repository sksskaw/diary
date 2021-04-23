package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class TodoDao {
	private DBUtil dbUtil;
	
	public int deleteTodoByMember(Connection conn, int memberNo) throws SQLException{
		this.dbUtil = new DBUtil();
		int returnResult = 0;
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_BY_MEMBER);
			stmt.setInt(1, memberNo);
			returnResult = stmt.executeUpdate();
		} finally {
			this.dbUtil.close(null, stmt, null);
		}
		
		return returnResult;
	}
}
