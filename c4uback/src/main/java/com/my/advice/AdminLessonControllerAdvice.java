package com.my.advice;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice(assignableTypes = {com.my.controller.AdminLessonController.class,
									com.my.controller.CategoryController.class})
public class AdminLessonControllerAdvice {

	@ResponseBody
	@ExceptionHandler
	public Map<String, Object> except(Exception e) {
		Map<String,Object> map = new HashMap<>();
		e.printStackTrace();
		map.put("status", -1);
		map.put("msg", e.getMessage());
		return map;
	}
}
