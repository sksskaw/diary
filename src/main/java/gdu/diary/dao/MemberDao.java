package gdu.diary.dao;

import java.sql.*;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberDao {
	private DBUtil dbUtil;
	
	// 회원 가입 메소드
	public int insertMember(Connection conn, Member member) throws SQLException{
		this.dbUtil = new DBUtil();
		int returnResult = 0;
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(MemberQuery.INSERT_MEMBER_BY_KEY);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			System.out.println("insertMember " + stmt);
			returnResult = stmt.executeUpdate();
		} finally {
			this.dbUtil.close(null, stmt, null);
		}
		
		return returnResult;
	}
	
	// 회원 탈퇴 메소드
	public int deleteMemberByKey(Connection conn, Member member) throws SQLException{
		this.dbUtil = new DBUtil();
		int returnResult = 0;
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(MemberQuery.DELETE_MEMBER_BY_KEY);
			stmt.setInt(1, member.getMemberNo());
			stmt.setString(2, member.getMemberPw());
			System.out.println("deleteMemberByKey " + stmt);
			returnResult = stmt.executeUpdate();
		} finally {
			this.dbUtil.close(null, stmt, null);
		}
		
		return returnResult;
	}
	
	// 회원 정보 가져오기
	public Member selectMemberByKey(Connection conn, Member member) throws SQLException {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(MemberQuery.SELECT_MEMBER_BY_KEY);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			System.out.println("selectMemberByKey " + stmt);
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
	
	// id 중복처리를 위한 메소드
	public String selectMemberIdByKey(Connection conn, String MemberId) throws SQLException {
		this.dbUtil = new DBUtil();
		String returnMemberId = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(MemberQuery.SELECT_MEMBER_ID_BY_KEY);
			stmt.setString(1, MemberId);
			System.out.println("selectMemberIdByKey " + stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMemberId = rs.getString("memberId");
			}
		} finally {
			this.dbUtil.close(rs, stmt, null);
		}
		return returnMemberId;
	}
	
	// 비밀번호 수정 메소드
	public int updateMemberPwByKey(Connection conn, Member member) throws SQLException {
		this.dbUtil = new DBUtil();
		int returnResult = 0;
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(MemberQuery.UPDATE_MEMBER_PW);
			stmt.setString(1, member.getMemberPw());
			stmt.setInt(2, member.getMemberNo());
			System.out.println("updateMemberPwByKey " + stmt);
			returnResult = stmt.executeUpdate();
		} finally {
			this.dbUtil.close(null, stmt, null);
		}
		
		return returnResult;
	}
}
