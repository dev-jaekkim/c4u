package com.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.exception.FindException;
import com.my.service.LessonService;
import com.my.vo.Lesson;

import lombok.extern.log4j.Log4j;

@CrossOrigin("*") // 모든요청 跨源资源共享
@RestController
@Log4j
public class MyPageController {

	@Autowired
	private LessonService service;

	@GetMapping(value = { "/mypage/mylesson/listWord", "/mypage/mylesson/listWord/{word}"})
	public Map<String, Object> lessonSearch(@PathVariable("word") Optional<String> optword) throws Exception {
		String word = null;
		List<Lesson> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 비즈니스 로직 호출
			if (optword.isPresent()) {
				word = optword.get();
				log.info("검색어" + word);
				list = service.findBySearch(word);
			} else {
				list = service.findAll();
				log.debug(list);
			}
			map.put("list", list);
			map.put("status", 1);
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	@GetMapping(value = "/mypage/mylesson/listStudentId/{studentId}")
	//public Map<String, Object> lessonOpen(){//@PathVariable int studentId) {
	public Map<String, Object> lessonOpen(@PathVariable int studentId) {	
	Map<String, Object> map = new HashMap<>();
	try {	
		//int studentId=5;
		List<Lesson> list = service.findByLessonOpen(studentId);
		map.put("status", 1);
		map.put("list", list);
	}catch(FindException e) {
		e.printStackTrace();
		map.put("status", -1);
		map.put("msg", e.getMessage());
	}
		return map;
		
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
	@PostMapping("/mypage/mycart/listinsert/{studentId}/{lessonId}")
	public Map<String, Object> insert(@PathVariable int studentId, @PathVariable int lessonId){
=======
	@GetMapping(value = "/mypage/mylesson/listLessonStatus/{studentId}{lessonStatus}")
	public Map<String, Object> lessonStatus(@PathVariable int studentId,
											List<Integer> lessonStatus){ //Getmapping은 @requestbody가 안된다.
			
>>>>>>> parent of cce0371 (canyi 2021-04-30 update)
		
=======
	@GetMapping(value = "/mypage/mylesson/listLessonStatus/{studentId}{lessonStatus}")
	public Map<String, Object> lessonStatus(@PathVariable int studentId,
											List<Integer> lessonStatus){ //Getmapping은 @requestbody가 안된다.
>>>>>>> c7f5792f746e574402b274d284f43a24c5b94014
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Lesson> list  = service.findByLessonStatus01234(studentId, lessonStatus);
			map.put("status", 1);
			map.put("list", list);
		} catch (FindException e) {
			
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
		}
		return null;
		
	}
	
	
}
