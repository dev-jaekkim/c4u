package com.my.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.service.LessonService;
import com.my.vo.Lesson;
import com.my.vo.Notice;
import com.my.vo.PageGroupBean;

import lombok.extern.log4j.Log4j;

@RestController

@CrossOrigin("*")
@Log4j
public class LessonController {
	@Autowired
	private LessonService service;

	@PostMapping(value = "/lesson/add" )
	public Map<String,Object> add(HttpServletRequest request, 
			MultipartFile thumbnail,
			MultipartFile detail, 
			Lesson lesson) throws Exception{
		log.info(lesson);
		Map<String, Object> map = new HashMap<>();
		String uploadFolder = "C:\\uploadFolder";
//		log.info("레슨 컨트롤러 lesson: " + lesson);
//		log.info("Upload File Name : " + thumbnail.getOriginalFilename());
//		log.info("Upload File Size : " + thumbnail.getSize());
//		log.info("Upload File Name : " + detail.getOriginalFilename());
//		log.info("Upload File Size : " + detail.getSize());

		service.add(lesson);

		int lessonId = lesson.getLessonId();

		String thumbnailName =  lessonId + "_thumbnail";
		String detailName =  lessonId + "_detail";
		File saveFile1 = new File(uploadFolder, thumbnailName);
		File saveFile2 = new File(uploadFolder, detailName);
		try { 
			thumbnail.transferTo(saveFile1);
			detail.transferTo(saveFile2);
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

	@PostMapping("/lesson/modify")
	public Map<String, Object> modify(MultipartFile thumbnail, MultipartFile detail, 
			Lesson lesson) throws Exception{
		Map<String, Object> map = new HashMap<>();
		String uploadFolder = "C:\\uploadFolder";
		log.info("레슨 컨트롤러 lesson: " + lesson);
		log.info("Upload File Name : " + thumbnail.getOriginalFilename());
		log.info("Upload File Size : " + thumbnail.getSize());
		log.info("Upload File Name : " + detail.getOriginalFilename());
		log.info("Upload File Size : " + detail.getSize());

		service.modify(lesson);

		int lessonId = lesson.getLessonId();

		String thumbnailName =  lessonId + "_thumbnail";
		String detailName =  lessonId + "_detail";
		File saveFile1 = new File(uploadFolder, thumbnailName);
		File saveFile2 = new File(uploadFolder, detailName);
		try { 
			thumbnail.transferTo(saveFile1);
			detail.transferTo(saveFile2);
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		map.put("status",1);
		return map;
	}
	
	@GetMapping(value={"/lesson/list",
					   "/lesson/list/{currentPage}",
					   "/lesson/list/{currentPage}/{word}"})
	public Map<String, Object> list(@PathVariable("currentPage") Optional<Integer> optCurrentPage,
			 						@PathVariable("word") Optional<String> optWord)throws Exception{
		String word = null;
		int currentPage = 1;
		List<Lesson> list = null;
		Map<String, Object> map = new HashMap<>();
		int cnt_per_page = 9;
		int totalCnt = service.findCnt();
		PageGroupBean<Lesson> pgb = null;
		if (optCurrentPage.isPresent()) {
			currentPage = optCurrentPage.get(); // AsInt();
		}
		if (optWord.isPresent()) {
			word = optWord.get();
			list = service.findByUnionPerPage(word, currentPage, cnt_per_page);
			totalCnt = service.findCnt(word);
		} else {
			list = service.findPerPage(currentPage, cnt_per_page);
		}
		pgb = new PageGroupBean<>(totalCnt, currentPage, list, cnt_per_page);
		log.debug(pgb);
		map.put("pgb", pgb);
		map.put("status", 1);
		return map;
	}
}
