package com.my.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.my.exception.AddException;
import com.my.exception.ModifyException;
import com.my.service.AdminLessonService;
import com.my.vo.LessonPenaltyStatus;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class AdminLessonController {
	@Autowired
	private AdminLessonService service;

	//심사 승인
	@PutMapping("/admin/lesson/modifylessonstatus/{lessonId}")
	public Map<String, Object> adminModifyLesson(@PathVariable int lessonId,Authentication auth) throws Exception {
		log.info(lessonId);
		Map<String, Object> map = new HashMap<>();
		if(auth != null) {
			service.modifyLesson(lessonId);
			map.put("status", 1);
		}else {
			log.warn("adminDetail"+auth);
			map.put("status", 9);
		}
		return map;
	}
	
	//심사 거절 
	@PostMapping("/insertpenaltystatus")
	public void insertLessonPenaltyStatus (@RequestBody LessonPenaltyStatus lessonPs) {
		try {
			service.addPenaltyStatus(lessonPs);
			
			
			
			
		} catch (AddException e) {
			e.printStackTrace();
		}
	}
	
	
}
