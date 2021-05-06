package com.my.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.service.LessonService;
import com.my.vo.Lesson;

import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin("*")
@Log4j
public class LessonController {
	@Autowired
	private LessonService service;
	
	@PostMapping(value = "/lesson/add")
	public Map<String,Object> add(MultipartFile[] uploadFile, 
	          @RequestBody Lesson lesson) throws Exception{
		log.info(lesson);
		Map<String, Object> map = new HashMap<>();
		service.add(lesson);
		map.put("status", 1);
		return map;
	}
	
	@GetMapping(value ="/lesson/{lessonId}")
	public Map<String, Object> findByLessonId (@PathVariable int lessonId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if(lessonId != 0) {
			Lesson lesson = service.findById(lessonId);
			map.put("lesson", lesson);
			map.put("status", 1);
		}
		return map;
	}
	
	@PutMapping(value ="/lesson/modify")
	public Map<String, Object> modify (
//			MultipartFile[] uploadFile, 
	          @RequestBody Lesson lesson) throws Exception{
		log.info(lesson);
//		String uploadFolder = "C:\\uploadFolder";
//
//		for (MultipartFile multipartFile : uploadFile) {
//			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
//			log.info("Upload File Size : " + multipartFile.getSize());
//			
//			String uploadFileName = multipartFile.getOriginalFilename();
//			File saveFile = new File(uploadFolder, uploadFileName);
//			try { 
//				multipartFile.transferTo(saveFile);
//				
//			}catch(Exception e) {
//				log.error(e.getMessage());
//			}
//		}
		Map<String, Object> map = new HashMap<>();
		service.modify(lesson);
		map.put("status", 1);
		return map;
	}
}
