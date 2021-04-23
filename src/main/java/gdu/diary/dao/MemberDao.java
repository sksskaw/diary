package gdu.diary.dao;

import java.sql.*;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberDao {
	private DBUtil dbUtil;
	
	public int deleteMemberByKey(Connection conn, Member member) throws SQLException{
		this.dbUtil = new DBUtil();
		int returnResult = 0;
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(MemberQuery.DELETE_MEMBER_BY_KEY);
			stmt.setInt(1, member.getMemberNo());
			stmt.setString(2, member.getMemberPw());
			returnResult = stmt.executeUpdate();
		} finally {
			this.dbUtil.close(null, stmt, null);
		}
		
		return returnResult;
	}
	
	public Member selectMemberByKey(Connection conn, Member member) throws SQLException {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(MemberQuery.SELECT_MEMBER_BY_KEY);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberNo(rs.getInt("memberNo"));
				returnMember.setMemberId(rs.getString("memberId"));
			}
		} finally {
			this.dbUtil.close(rs, stmt, null);
		}
		return returnMember;
	}
}
