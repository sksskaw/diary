package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Todo;

public class DiaryService {
	
	private DBUtil dbUtil;
	private TodoDao todoDao;
	
	// 달력 출력 정보 가져오기
	public Map<String, Object> getDiary(int memberNo, String targetYear, String targetMonth) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Calendar target = Calendar.getInstance();
		
		if(targetYear != null) {
			target.set(Calendar.YEAR, Integer.parseInt(targetYear));
		}
		if(targetMonth != null) {
			target.set(Calendar.MONTH, Integer.parseInt(targetMonth));
		}
		
		target.set(Calendar.DATE, 1); // 해당 달의 시작 요일을 알기 위해 1일로 설정
		// 해당 달 앞의 공백
		int startBlank = target.get(Calendar.DAY_OF_WEEK) - 1;
		// 해당 달의 마지막 날짜
		int endDay = target.getActualMaximum(Calendar.DATE);
		// 해당 달 뒤의 공백
		int endBlank = 0;
		if((startBlank + endDay)%7 != 0) { // 7로 나누어 떨어지면 뒤의 공백 필요없음
			endBlank = 7 - (startBlank + endDay)%7; // 이만큼의 공백을 더 그려야함
		}
		
		// 1. 달력 출력 정보
		returnMap.put("targetYear", target.get(Calendar.YEAR));
		returnMap.put("targetMonth", target.get(Calendar.MONTH));
		returnMap.put("startBlank", startBlank);
		returnMap.put("endDay", endDay);
		returnMap.put("endBlank", endBlank);
		
		
		// 2. targetYear,targetMonth(0이면 1월, 1이면 2월)에 해당하는 todo목록 가져와서 추가
		// 3. d-day 리스트 가져오기
		this.dbUtil = new DBUtil();
		this.todoDao = new TodoDao();
		List<Todo> todoList = null; // todo 목록
		List<Map<String, Object>> ddayList = null; // d-day 목록
		Connection conn = null;		
		try {
			conn = this.dbUtil.getConnection();
			todoList = this.todoDao.selectTodoListByDate(conn, memberNo, target.get(Calendar.YEAR), target.get(Calendar.MONTH)+1);
			ddayList = this.todoDao.selectTodoDdayList(conn, memberNo);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		returnMap.put("todoList", todoList);
		returnMap.put("ddayList", ddayList);

		return returnMap;
	}
}
