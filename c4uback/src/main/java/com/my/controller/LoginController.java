package com.my.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {
	@RequestMapping("/accessError")
	public void accessError(Authentication auth, Model model) {
		//인증된 유저의 이름을 가져올 수 있다. 
		if(auth != null) {
			String userName = auth.getName();
			model.addAttribute("msg", userName+"은 접근불가한 사이트입니다.");
		}
	}
	
	@GetMapping("/myLogin")
	/**
	 * 인증실패된 후 또는 로그아웃후에 로그인페이지로 리다이렉트된다. 이때 queryString이 전달됨
	 * error         logout
	 */
	public void getMyLogin(String error, String logout, Model model) {
		if(error != null) { //인증실패인 경우 전달된 요청전달데이터 error
			//error내용을 view에 전달
			//model.addAttribute("error", "인증실패되었습니다"); //?
			model.addAttribute("error", error);
			
		}
		if(logout != null) { //로그아웃된 경우 
			model.addAttribute("logout", "로그아웃되었습니다");
		}
		log.debug("로그인 요청");
	}
	
	@GetMapping("/myLogout")
	public void getMyLogout() {}
	
	@PostMapping("/myLogout")
	public void postMyLogout() { //실제로 하는 일은 없고 viewer와 연결만 하면 된다. 
	}
	
//	@PostMapping("/myLogin")
//	public void postMyLogin() {
//	}
}
