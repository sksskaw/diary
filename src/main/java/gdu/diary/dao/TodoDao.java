package gdu.diary.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.*;

public class TodoDao {
	private DBUtil dbUtil;
	
	// 해당 달에 있는 모든 일정 가져오기
	public List<Todo> selectTodoListByDate(int memberNo, int targetYear, int targetMonth) {
		this.dbUtil = new DBUtil();
		
		// SQL 파라미터의 값을 Map으로 묶어서 객체를 넘겨준다. 
		Map<String, Integer> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("targetYear", targetYear);
		map.put("targetMonth", targetMonth);
		
		SqlSession sqlSession = this.dbUtil.getSqlSession();
		return sqlSession.selectList("gdu.diary.dao.TodoMapper.selectTodoListByDate",map);
	}
	
	// 일정 추가
	public int insertTodo(Todo todo){
		this.dbUtil = new DBUtil();
		int result = 0;
		
		SqlSession sqlSession = this.dbUtil.getSqlSession();
		result = sqlSession.insert("gdu.diary.dao.TodoMapper.insertTodo",todo);
		sqlSession.commit();
		
		return result;
	}
	
	// 일정 삭제(회원탈퇴 시 해당 계정이 작성한 모든 일정 삭제) MemberService에서 호출
	// 트랜잭션 처리가 난해해서 member_no 외래키 옵션 ON DELETE CASCADE로 수정
	// 해당 매소드 사용 x
	public int deleteTodoByMember(int memberNo){
		this.dbUtil = new DBUtil();
		int result = 0;
		
		SqlSession sqlSession = this.dbUtil.getSqlSession();
		result = sqlSession.delete("gdu.diary.dao.TodoMapper.deleteTodoByMember",memberNo);
		sqlSession.commit();
		
		return result;
	}
	
	// 일정 상세정보 가져오기
	public List<Todo> selectTodoOne(int todoNo){
		this.dbUtil = new DBUtil();
		SqlSession sqlSession = this.dbUtil.getSqlSession();
		return sqlSession.selectList("gdu.diary.dao.TodoMapper.selectTodoOne",todoNo);
	}
	
	// 일정 수정 메소드
	public int updateTodo(Todo todo){
		this.dbUtil = new DBUtil();
		int result = 0;
		
		SqlSession sqlSession = this.dbUtil.getSqlSession();
		result = sqlSession.update("gdu.diary.dao.TodoMapper.updateTodo",todo);
		sqlSession.commit();
		
		return result;
	}
	
	// 일정 삭제 메소드
	public int deleteTodoOne(int todoNo){
		this.dbUtil = new DBUtil();
		int result = 0;
		
		SqlSession sqlSession = this.dbUtil.getSqlSession();
		result = sqlSession.delete("gdu.diary.dao.TodoMapper.deleteTodoOne",todoNo);
		sqlSession.commit();
		
		return result;
	}
	
	// d-day 리스트 가져오기
	public List<Map<String, String>> selectTodoDdayList(int memberNo) {
		this.dbUtil = new DBUtil();
		SqlSession sqlSession = this.dbUtil.getSqlSession();
		return sqlSession.selectList("gdu.diary.dao.TodoMapper.selectTodoDdayList",memberNo);
	}
}
