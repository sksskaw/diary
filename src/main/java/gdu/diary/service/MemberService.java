package gdu.diary.service;

import java.sql.*;

import gdu.diary.dao.*;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.*;

public class MemberService {
	private DBUtil dbUtil;
	private MemberDao memberDao;
	private TodoDao todoDao;
	
	// 회원 가입 메소드
	public int addMemberByKey(Member member) {
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		int memberDaoResult = 0;
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			// 아이디 중복처리
			if(this.memberDao.selectMemberIdByKey(conn, member.getMemberId()) != null) {
				System.out.println("이미 사용중인 id입니다.");
				conn.commit();
				return memberDaoResult;
			}
			memberDaoResult = this.memberDao.insertMember(conn, member);
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
		return memberDaoResult;
	}
	
	// 회원 탈퇴 메소드
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
		
		// 트랜잭션이 정상으로 처리되었으면 -> todoDao, memberDao 결과값이 모두 0,0이 아니라면
		return (todoDaoResult + memberDaoResult) > 0;
	}
	
	// 회원 정보 가져오기
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
