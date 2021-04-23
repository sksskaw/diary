package gdu.diary.service;

import java.sql.*;

import gdu.diary.dao.*;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.*;

public class MemberService {
	private DBUtil dbUtil;
	private MemberDao memberDao;
	private TodoDao todoDao;
	
	public boolean removeMemberByKey(Member member) {
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao(); 
		this.todoDao = new TodoDao(); 
		int todoDaoResult = 0;
		int memberDaoResult = 0;
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			todoDaoResult = this.todoDao.deleteTodoByMember(conn, member.getMemberNo());
			memberDaoResult = this.memberDao.deleteMemberByKey(conn, member);
			conn.commit();
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			this.dbUtil.close(null, null, conn);
		}
		
		return (todoDaoResult + memberDaoResult) >0;
	}
	
	public Member getMemberByKey(Member member) {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		this.memberDao = new MemberDao(); 
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			returnMember = this.memberDao.selectMemberByKey(conn, member);
			conn.commit();
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.dbUtil.close(null, null, conn);
		}
		return returnMember;
	}
}
