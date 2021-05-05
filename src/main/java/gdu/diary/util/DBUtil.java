package gdu.diary.util;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtil {
	
	public SqlSession getSqlSession() {
		
		SqlSession sqlSession = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
			sqlSession = ssf.openSession();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession;
	}
	
	// 1. DB 연동
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/diary","root","378044");
			conn.setAutoCommit(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 2. DB객체 (conn, stmt, rs) 해제
	public void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
