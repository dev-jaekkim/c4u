package com.my.controller;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import com.my.service.AdminLessonService;
import com.my.vo.Lesson;
import com.my.vo.LessonPenalty;
import com.my.vo.LessonPenaltyStatus;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class AdminLessonController {
	@Autowired
	private AdminLessonService service;

	//심사 승인
	@PutMapping("/admin/lesson/modifylessonstatus/{lessonId}")
	public Map<String, Object> adminModifyLesson(@PathVariable int lessonId
			//			,Authentication auth 
			)throws Exception {
		log.info("강좌번호" + lessonId);
		Map<String, Object> map = new HashMap<>();
		//		if(auth != null) {
		service.modifyLesson(lessonId);
		map.put("status", 1);
		//		}else {
		//			log.warn("adminDetail"+auth);
		//			map.put("status", 9);
		//		}
		return map;
	}

	//심사 거절 
	@PostMapping("/admin/lesson/insertpenaltystatus")
	public Map<String, Object>  insertLessonPenaltyStatus (@RequestBody LessonPenaltyStatus lessonPs) throws Exception {
		log.info(lessonPs);
		Map<String, Object> map = new HashMap<>();
		service.addPenaltyStatus(lessonPs);
		map.put("status", 1);
		return map;
	}
	
	@GetMapping("/admin/lesson/lessonpenaltyall")
	public Map<String, Object> findLessonPenaltyAll() throws Exception{
		Map<String, Object> map = new HashMap<>();
		List<LessonPenalty> list = service.findLessonPenaltyAll();
		map.put("lessonPenalty", list);
		map.put("status", 1);
		return map;
	}
	
	//해당 강좌의 lessonps 조회 
	@GetMapping("/admin/lesson/lessonps/{lessonId}")
	public Map<String,Object> findLessonPs(@PathVariable int lessonId) throws Exception{
		Map<String, Object> map = new HashMap<>();
		List<LessonPenalty> list = service.findLessonPs(lessonId);
		map.put("lessonPenalty", list);
		map.put("status", 1);
		return map;
	}
	
//	@GetMapping("/admin/lesson/detail/{lessonId}")
//	public Map<String,Object> adminLessonDetail (@PathVariable int lessonId) throws Exception{
//		Map<String, Object> map = new HashMap<>();
//		List<Lesson> list = service.findDetailByLessonId(lessonId);
//		map.put("lesson",list);
//		map.put("status",1);
//		return map;
//	}

}
