package com.my.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginStatus {

	@GetMapping(value = "/loginstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	protected Map<String, Object> login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
//		String loginedId = (String) session.getAttribute("loginInfo");
		
		Integer wrapperStudent_id = (Integer)session.getAttribute("loginInfo");
		
		
		
		if (wrapperStudent_id != null) {
			log.debug("로그인O, 권한 O");
			map.put("status", 1);
			map.put("studentId", wrapperStudent_id);
		} else {
			log.debug("로그인X, 권한 X");
			map.put("status", -1);
		}
		return map;
	}

	@GetMapping(value = "/adminloginstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	protected Map<String, Object> adminLogin(HttpServletRequest request, HttpServletResponse response, 
											Authentication auth)
			throws ServletException, IOException {

		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		String loginedId = (String) session.getAttribute("loginInfo");

		List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();
        String strAuth = authorities.get(0).getAuthority();
		
        log.debug("strAuth: "+strAuth);
        
		if (strAuth.equals("ROLE_ADMIN")) {
			log.debug("로그인O, 권한 O");
			map.put("adminRole", 1);
		} else {
			log.debug("로그인X, 권한 X");
			map.put("adminRole", -1);
		}
		return map;
	}

}
