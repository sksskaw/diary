package gdu.diary.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/auth/*") //http://localhost/diary/auth/* 로 요청시 가장먼저 이쪽으로 온다.
public class AuthFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		System.out.println("AuthFilter 실행");
		HttpSession session = httpRequest.getSession();
		
		if(session.getAttribute("sessionMember") == null) {
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
			return;
		}
		
		chain.doFilter(request, response);
	}

}
