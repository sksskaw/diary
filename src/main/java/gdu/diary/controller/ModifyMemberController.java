package gdu.diary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;

@WebServlet("/auth/modifyMember")
public class ModifyMemberController extends HttpServlet {
	
	private MemberService memberService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/modifyMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.memberService = new MemberService();
		String currentPassword = request.getParameter("currentPassword"); //현재 비밀번호
		String changPassword = request.getParameter("changPassword");     //수정 비밀번호
		
		// 로그인 id 정보 가져오기
		HttpSession session = request.getSession();
		int memberNo = ((Member)session.getAttribute("sessionMember")).getMemberNo();
		String memberId = ((Member)session.getAttribute("sessionMember")).getMemberId();
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(currentPassword);
		
		// 입력한 현재 비밀번호가 맞는지 확인
		Member returnMember = this.memberService.getMemberByKey(member);
		if(returnMember == null) {
			System.out.println("잘못된 비밀번호입니다.");
			response.sendRedirect(request.getContextPath()+"/auth/modifyMember");
			return;
		}
			
		// 비밀번호 수정 메소드
		member.setMemberNo(memberNo);
		member.setMemberPw(changPassword);
		this.memberService.modifyMemberByKey(member);
		response.sendRedirect(request.getContextPath()+"/auth/logout");
		System.out.println("변경된 비밀번호로 다시 로그인 해주세요");
		return;
	}

}