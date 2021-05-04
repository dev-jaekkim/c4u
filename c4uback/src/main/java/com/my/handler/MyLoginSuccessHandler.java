package com.my.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;
@Log4j
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
										HttpServletResponse response,
										Authentication auth) throws IOException, ServletException {
		log.warn("auth: " + auth);
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();
		String strAuth = authorities.get(0).getAuthority();
		
		Cookie cookie = new Cookie("auth", strAuth);
        cookie.setPath("/"); //쿠키를 설정한 경로 root라고 생각하면 됨
        response.addCookie(cookie); //쿠키를 응답
		
		if (strAuth.equals("ROLE_ADMIN")) {
			response.sendRedirect("http://localhost:8888/c4ufront/admin_notice_list.html");
		}else {
			response.sendRedirect("http://localhost:8888/c4ufront/maindraft1.html");
		}
	}
}