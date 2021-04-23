package gdu.diary.service;

import java.util.*;

public class DiaryService {
	
	public Map<String, Object> getDiary(String targetYear, String targetMonth) {
		
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
			endBlank = 7 - (startBlank + endDay)%7; // 이만큼 공백을 더 그려야함
		}
		
		returnMap.put("targetYear", target.get(Calendar.YEAR));
		returnMap.put("targetMonth", target.get(Calendar.MONTH));
		returnMap.put("startBlank", startBlank);
		returnMap.put("endDay", endDay);
		returnMap.put("endBlank", endBlank);
		
		return returnMap;
	}

}
