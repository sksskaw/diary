package gdu.diary.dao;

import java.sql.*;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberDao {
	private DBUtil dbUtil;
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