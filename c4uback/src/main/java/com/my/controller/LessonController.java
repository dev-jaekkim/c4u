package com.my.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.LessonService;
import com.my.vo.Lesson;

import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin("*")
@Log4j
public class LessonController {
	@Autowired
	private LessonService service;
	
	//2021-04-29 김보람
	@PostMapping(value = "/lesson/add")
	public Map<String,Object> add(MultipartFile[] uploadFile, 
	          @RequestBody Lesson lesson) throws AddException{
		log.info(lesson);
		Map<String, Object> map = new HashMap<>();
		service.add(lesson);

		map.put("status", 1);
		return map;
	}
	
	@GetMapping(value ="/lesson/{lessonId}")
	public Map<String, Object> findByLessonId (@PathVariable int lessonId) throws FindException {
		Map<String, Object> map = new HashMap<>();
		if(lessonId != 0) {
			Lesson lesson = service.findById(lessonId);
			map.put("lesson", lesson);
			map.put("status", 1);
		}
		return map;
	}
}
