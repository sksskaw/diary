package gdu.diary.controller;

import java.io.IOException;
import java.util.*;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import gdu.diary.service.DiaryService;
import gdu.diary.vo.Member;


@WebServlet("/auth/diary")
public class DiaryController extends HttpServlet {

	private DiaryService diaryService;
	
	// XML 테그값 가져오기
	private static String getTagValue(String tag, Element ele) {

        NodeList nodeList = ele.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nodeList.item(0);

        if(nValue == null) {
            return null;
        }
        return nValue.getNodeValue();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.diaryService = new DiaryService();
		HttpSession session = request.getSession(); // 세션 코드 추가
		int memberNo = ((Member)session.getAttribute("sessionMember")).getMemberNo();
		String targetYear = request.getParameter("targetYear"); // "2021"
		String targetMonth = request.getParameter("targetMonth"); // 4월이면...3, 5월이면 4,...
		
		Map<String, Object> diaryMap = this.diaryService.getDiary(memberNo, targetYear, targetMonth);
		
		request.setAttribute("diaryMap", diaryMap);
		
		
		// API 요청을 위한 년, 월 변수
		int year = (Integer)diaryMap.get("targetYear");
		int month = (Integer)diaryMap.get("targetMonth")+1;
		// 월 변수는 2자리로 요청을 보내야함.
		String strMonth = null;
		if(month < 10) {
			strMonth = "0" + month;
		} else {
			strMonth = ""+month;
		}
		
		// 해당 달 공휴일 리스트
		List<Map<String, Object>> holidayList = new ArrayList<>();
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		
		String apiURI = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?"; // 공휴일 요청
		String encodingKey="serviceKey=ttfhwH%2FrVrtXytNIhRm2ubOFIWS4DzeHjRW%2F%2B%2F%2FA3dHFhrGoJ90pDWrkWKflhOwdDENX%2BUMy4QmMcUGWzqlhkA%3D%3D";
		//String decodingKey="ttfhwH/rVrtXytNIhRm2ubOFIWS4DzeHjRW/+//A3dHFhrGoJ90pDWrkWKflhOwdDENX+UMy4QmMcUGWzqlhkA==";
		BufferedReader br = null;
		
		try {
			String uriStr = apiURI + encodingKey + "&solYear=" + year + "&solMonth=" + strMonth;

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    Document document = builder.parse(uriStr);
		    
		    document.getDocumentElement().normalize();
		    NodeList itemList = document.getElementsByTagName("item");
		    
		    System.out.println(year + "년 " + strMonth + "월 휴일 정보");
		    
		    for(int temp = 0; temp < itemList.getLength(); temp++){
		    	Node nNode = itemList.item(temp);
		    	
		    	if(nNode.getNodeType() == Node.ELEMENT_NODE){
		    		Element eElement = (Element) nNode;
		    		System.out.println("dateName  : " + getTagValue("dateName", eElement));
		    		System.out.println("locdate  : " + getTagValue("locdate", eElement).substring(6,8));
		    		
		    		Map<String, Object> m = new HashMap<>();
		    		m.put("dateName", getTagValue("dateName", eElement));
		    		
		    		String locdate = "";
		    		
		    		// 문자열 처리
		    		if(getTagValue("locdate", eElement).substring(6,7).equals("0")) {
		    			locdate = getTagValue("locdate", eElement).substring(7,8);
		    		} else {
		    			locdate = getTagValue("locdate", eElement).substring(6,8);
		    		}
		    		
		    		m.put("locdate", locdate);
		    		holidayList.add(m);
		    	}
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("holidayList", holidayList);
		request.getRequestDispatcher("/WEB-INF/view/diary.jsp").forward(request, response);
	}
}