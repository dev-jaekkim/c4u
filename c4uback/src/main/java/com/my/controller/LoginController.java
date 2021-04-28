package com.my.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;
@Log4j
public class LoginController {
	@GetMapping("/myLogin")
	public void getLogin(HttpServletResponse response) throws IOException {
		log.debug("로그인 !");
//		response.sendRedirect("http://localhost:8888/c4ufront/login.html");
	}
}
