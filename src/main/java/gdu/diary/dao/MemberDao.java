package gdu.diary.dao;

import java.sql.*;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberDao {
	private DBUtil dbUtil;
	
	// 회원 가입 메소드
	public int  insertMember(Member member){
		this.dbUtil = new DBUtil();
		int result = 0;
		
		SqlSession sqlSession = this.dbUtil.getSqlSession();
		result = sqlSession.insert("gdu.diary.dao.MemberMapper.insertMember",member);
		sqlSession.commit();
		
		return result;
	}
	
	// 회원 탈퇴 메소드
	public int deleteMemberByKey(Member member){
		this.dbUtil = new DBUtil();
		int result = 0;
		
		SqlSession sqlSession = this.dbUtil.getSqlSession();
		result = sqlSession.delete("gdu.diary.dao.MemberMapper.deleteMemberByKey",member);
		if(result == 0) {
			System.out.println("비밀번호가 틀렸습니다.");
			return 0;
		}
		sqlSession.commit();
		
		return result;
	}
	
	// 회원 정보 가져오기
	public List<Member> selectMemberByKey(Member member){

		this.dbUtil = new DBUtil();
		
		SqlSession sqlSession = this.dbUtil.getSqlSession();
		return sqlSession.selectList("gdu.diary.dao.MemberMapper.selectMemberByKey",member);
	}
	
	// id 중복처리를 위한 메소드
	public List<Member> selectMemberIdByKey(String memberId){
		this.dbUtil = new DBUtil();
		
		SqlSession sqlSession = this.dbUtil.getSqlSession();
		return sqlSession.selectList("gdu.diary.dao.MemberMapper.selectMemberIdByKey",memberId);
	}
	
	// 비밀번호 수정 메소드
	public int updateMemberPwByKey(Member member){
		this.dbUtil = new DBUtil();
		int result = 0;
		
		SqlSession sqlSession = this.dbUtil.getSqlSession();
		result = sqlSession.update("gdu.diary.dao.MemberMapper.updateMemberPwByKey",member);
		sqlSession.commit();
		
		return result;
	}
}
