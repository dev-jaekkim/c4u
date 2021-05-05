package com.my.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@CrossOrigin("*")  
@ControllerAdvice(assignableTypes = com.my.controller.StudentController.class)
public class StudentControllerAdvice {

	@ResponseBody
	@ExceptionHandler
	public Map<String, Object> except(Exception e) {
		Map<String,Object> map = new HashMap<>();
		map.put("status", -1);
		map.put("msg", e.getMessage());
		return map;
	}
}


