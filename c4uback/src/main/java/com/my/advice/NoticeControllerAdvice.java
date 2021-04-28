package com.my.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(assignableTypes = com.my.controller.NoticeController.class)
public class NoticeControllerAdvice {

	@ResponseBody
	@ExceptionHandler
	public Map<String, Object> except(Exception e) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", -1);
		map.put("msg", e.getMessage());
		return map;
	}
}