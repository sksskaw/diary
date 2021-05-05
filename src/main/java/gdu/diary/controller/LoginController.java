package gdu.diary.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private MemberService memberService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.memberService = new MemberService();
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		// System.out.println(member);
		
		List<Member> returnMember = this.memberService.getMemberByKey(member);
		
		if(returnMember.size() == 0) {
			System.out.println("가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		} else {
			System.out.println("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("sessionMember", returnMember.get(0)); // returnMember.size()가 0이 아니면 무조건 값은 하나. 기본키로 검색했기 때문에
		}
		response.sendRedirect(request.getContextPath()+"/auth/diary");
	}
}