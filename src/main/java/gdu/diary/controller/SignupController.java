package gdu.diary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;


@WebServlet("/signUp")
public class SignupController extends HttpServlet {

	private MemberService memberService;
	
	// 회원 정보 입력 폼으로
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/signUp.jsp").forward(request, response);
	}

	// 회원 정보 액션 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		memberService = new MemberService();
		
		String memberId = null;
		String memberPw = null;
		
		// 수집
		if(request.getParameter("memberId") != null) {
			memberId = request.getParameter("memberId");
		}
		
		if(request.getParameter("memberPw") != null) {
			memberPw = request.getParameter("memberPw");
		}
		
		// vo 생성
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// service 호출
		if(memberService.checkMemberIdByKey(member.getMemberId()) != null) { // id가 중복이면 입력폼으로
			System.out.println("이미 사용중인 id입니다.");
			response.sendRedirect(request.getContextPath() + "/signUp");
			return;
		} else {
			int result = memberService.insertMemberByKey(member);
			if(result == 0) { // DB 입력 오류 시 입력 폼으로
				System.out.println("입력 오류");
				response.sendRedirect(request.getContextPath() + "/signUp");
				return;
			}
		}
		
		// 정상 입력 시 로그인 폼으로
		response.sendRedirect(request.getContextPath() + "/login");
	}

}
