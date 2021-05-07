package com.my.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.my.dao.StudentDAO;
import com.my.exception.FindException;
import com.my.vo.Student;

import lombok.extern.log4j.Log4j;
@Log4j
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	StudentDAO dao;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
										HttpServletResponse response,
										Authentication auth) throws IOException, ServletException {
		
		log.info("auth: "+auth);
		log.warn("auth: " + auth);

		List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();
		String strAuth = authorities.get(0).getAuthority();
		
		Cookie cookie = new Cookie("auth", strAuth);
        cookie.setPath("/"); //쿠키를 설정한 경로 root라고 생각하면 됨 
        response.addCookie(cookie); //쿠키를 응답

        auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Cookie strusername = new Cookie("username", username);
        strusername.setPath("/");
        response.addCookie(strusername);
        
//        Cookie studentIdCookie = new Cookie("studentId","aaa"+ String.valueOf(1));
//		studentIdCookie.setPath("/");
//		response.addCookie(studentIdCookie);
		
//        Cookie strstudentId_val = new Cookie ("studentId_val", "1");
//        strstudentId_val.setPath("/");
//        response.addCookie(strstudentId_val);
        
  //      StudentDAOOracle dao = new StudentDAOOracle();
        Student s;
			try {
				s = dao.selectByEmail(username);
				int studentId = s.getStudentId();
//				String strstudentId = Integer.toString(studentId);
				
				
				Cookie studentIdCookie = new Cookie("studentId",""+ String.valueOf(studentId));
				studentIdCookie.setPath("/");
				response.addCookie(studentIdCookie);
				
			} catch (FindException e) {
				e.printStackTrace();
			}
		
		if (strAuth.equals("ROLE_ADMIN")) {
			response.sendRedirect("http://localhost:8888/c4ufront/admin_evaluation_list.html");
		}else {
			response.sendRedirect("http://localhost:8888/c4ufront/mainpage.html");
		}
	}
}