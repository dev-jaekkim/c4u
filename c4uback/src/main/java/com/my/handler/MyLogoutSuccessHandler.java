package com.my.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		log.info("MyLogoutSuccessHandler auth=" + authentication);
		response.getWriter().print("onLogoutSuccess");
		if (authentication != null && authentication.getDetails() != null) {
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
			String strAuth = authorities.get(0).getAuthority();

			try {
				request.getSession().invalidate();

				Cookie cookie = new Cookie("auth", strAuth);
				cookie.setPath("/"); //쿠키를 설정한 경로 root라고 생각하면 됨
				cookie.setMaxAge(0);
				response.addCookie(cookie);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}

}
