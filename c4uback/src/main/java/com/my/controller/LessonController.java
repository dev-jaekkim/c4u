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
	public Map<String,Object> add(MultipartFile uploadFile1, MultipartFile uploadFile2, Lesson lesson) throws Exception{
		log.info(lesson);
		Map<String, Object> map = new HashMap<>();
		String uploadFolder = "C:\\uploadFolder";
		log.info("레슨 컨트롤러 lesson: " + lesson);
		log.info("Upload File Name : " + uploadFile1.getOriginalFilename());
		log.info("Upload File Size : " + uploadFile1.getSize());
		log.info("Upload File Name : " + uploadFile2.getOriginalFilename());
		log.info("Upload File Size : " + uploadFile2.getSize());

		service.add(lesson);

		int lessonId = lesson.getLessonId();

		String uploadFileName =  lessonId + "_thumbnail";
		String uploadFileName2 =  lessonId + "_detail";
		File saveFile1 = new File(uploadFolder, uploadFileName);
		File saveFile2 = new File(uploadFolder, uploadFileName2);
		try { 
			uploadFile1.transferTo(saveFile1);
			uploadFile2.transferTo(saveFile2);
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		map.put("status",1);
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

	//	@PutMapping(value ="/lesson/modify")
	//	public Map<String, Object> modify (
	////			MultipartFile[] uploadFile, 
	//	          @RequestBody Lesson lesson) throws Exception{
	//		log.info(lesson);
	////		String uploadFolder = "C:\\uploadFolder";
	////
	////		for (MultipartFile multipartFile : uploadFile) {
	////			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
	////			log.info("Upload File Size : " + multipartFile.getSize());
	////			
	////			String uploadFileName = multipartFile.getOriginalFilename();
	////			File saveFile = new File(uploadFolder, uploadFileName);
	////			try { 
	////				multipartFile.transferTo(saveFile);
	////				
	////			}catch(Exception e) {
	////				log.error(e.getMessage());
	////			}
	////		}
	//		Map<String, Object> map = new HashMap<>();
	//		service.modify(lesson);
	//		map.put("status", 1);
	//		return map;
	//	}
	@PostMapping("/lesson/modify")
	public Map<String, Object> modify(MultipartFile uploadFile1, MultipartFile uploadFile2, 
			Lesson lesson) throws Exception{
		Map<String, Object> map = new HashMap<>();
		String uploadFolder = "C:\\uploadFolder";
		log.info("레슨 컨트롤러 lesson: " + lesson);
		log.info("Upload File Name : " + uploadFile1.getOriginalFilename());
		log.info("Upload File Size : " + uploadFile1.getSize());
		log.info("Upload File Name : " + uploadFile2.getOriginalFilename());
		log.info("Upload File Size : " + uploadFile2.getSize());

		service.modify(lesson);

		int lessonId = lesson.getLessonId();

		String uploadFileName =  lessonId + "_thumbnail";
		String uploadFileName2 =  lessonId + "_detail";
		File saveFile1 = new File(uploadFolder, uploadFileName);
		File saveFile2 = new File(uploadFolder, uploadFileName2);
		try { 
			uploadFile1.transferTo(saveFile1);
			uploadFile2.transferTo(saveFile2);
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		map.put("status",1);
		return map;

	}
}
