package gdu.diary.service;

import java.sql.*;

import gdu.diary.dao.MemberDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberService {
	private DBUtil dbUtil;
	private MemberDao memberDao;
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
