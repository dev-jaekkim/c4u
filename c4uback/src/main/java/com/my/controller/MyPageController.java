package com.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.dao.LPSDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.RemoveException;
import com.my.service.CartService;
import com.my.service.LPSService;
import com.my.service.LessonService;
import com.my.vo.LPS;
import com.my.vo.Lesson;
import com.my.vo.Student;

import lombok.extern.log4j.Log4j;

@CrossOrigin("*") // 모든요청 跨源资源共享
@RestController
@Log4j
public class MyPageController {

	// 2021-04-29  내가 개설한 강좌 시작
 	@Autowired
	private LessonService LessonService; // service > LessonService

	@Autowired
	private CartService CartService;
	
	@Autowired
	private LPSService LPSService;

	@GetMapping(value = {"/mypage/mylesson/listWord", "/mypage/mylesson/listWord/{word}"})
	public Map<String, Object> lessonSearch(@PathVariable("word") Optional<String> optword) throws Exception {
		String word = null;
		List<Lesson> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 비즈니스 로직 호출
			if (optword.isPresent()) {
				word = optword.get();
				log.info("검색어" + word);
				list = LessonService.findBySearch(word);
			} else {
				list = LessonService.findAll();
				log.debug(list);
			}
			map.put("list", list);
			log.debug(word);
			map.put("status", 1);
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	@GetMapping(value = "/mypage/mylesson/listStudentId/{studentId}")
	// public Map<String, Object> lessonOpen(){//@PathVariable int studentId) {
	public Map<String, Object> lessonOpen(@PathVariable int studentId) {
		Map<String, Object> map = new HashMap<>();
		try {
			// int studentId=5;
			List<Lesson> list = LessonService.findByLessonOpen(studentId);
			map.put("status", 1);
			map.put("list", list);
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
		}
		return map;

	}

	@GetMapping(value = "/mypage/mylesson/listLessonStatus/{studentId}{lessonStatus}") // 500에러
	public Map<String, Object> lessonStatus(@PathVariable int studentId, List<Integer> lessonStatus) { // Getmapping은 @requestbody가  안된다.

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<Lesson> list = LessonService.findByLessonStatus01234(studentId, lessonStatus);
			map.put("status", 1);
			map.put("list", list);
		} catch (FindException e) {

			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
		}
		return map;

	}

	//내가 좋아한 강좌 시작
	@GetMapping(value = "/mypage/mycart/listSelectById/{studentId}")
	public Map<String, Object> selectById(@PathVariable int studentId) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<Lesson> list = CartService.findById(studentId);
			map.put("status", 1);
			map.put("list", list);
		} catch (FindException e) {

			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
		}

		return map;

	}
	

	@PostMapping("/mypage/mycart/listinsert/{studentId}/{lessonId}")
	public Map<String, Object> insert(@PathVariable int studentId, @PathVariable int lessonId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CartService.add(lessonId, studentId);
			map.put("status", 1);
		} catch (AddException e) {
			
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
		}
		return map;
		
	}

	
	@DeleteMapping("/mypage/mycart/listdelete/{studentId}/{lessonId}")
	public Map<String, Object> remove(@PathVariable int studentId,
									  @PathVariable int lessonId){
	
	Map<String, Object> map  = new HashMap<String, Object>();
	
	try {
		CartService.remove(lessonId, studentId);
		map.put("status", 1);
	} catch (RemoveException e) {
	
		e.printStackTrace();
		map.put("status", -1);
		map.put("msg", e.getMessage());
	}
	
	return map;	
}
	
	@GetMapping("/mypage/mycart/listSelectAllCount/{studentId}")
	public Map<String, Object> selectAllCount(@PathVariable int studentId){
		
		Map<String, Object> map = new HashMap<>();
		
		try {
			int count = CartService.findAllCount(studentId);
			map.put("status", 1);
			map.put("list", count);
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
		}	
		return map;
	}
	
	@GetMapping("/mypage/mycart/selectByPage/{studentId}/{currentPage}/{cnt_per_page}")
	public Map<String, Object> selectByPage(@PathVariable int studentId,
											@PathVariable int currentPage,
											@PathVariable int cnt_per_page){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			List<Lesson> list =  CartService.findByPage(currentPage, currentPage, studentId);
			map.put("status", 1);
			map.put("list", list);
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
		}
		return map;
		
	}
	
	//마이페이지 강좌관리
	@GetMapping("/mypage/myStudent/selectByStudentInformation/{lessonId}")
	public Map<String, Object> selectByStudentInformation(@PathVariable int lessonId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			List<Student> list = LPSService.findByStudentInformation(lessonId);
			map.put("status", 1);
			map.put("list", list);
			
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
		}
		
		return map;
		
	}
	
	//마이페이지  수강현황
	
	@GetMapping("/mypage/myStudentLesson/selectByPageStudentLesson/{studentId}/{currentPage}/{cnt_per_page}")
	public Map<String, Object> selectByPageStudentLesson(@PathVariable int studentId,
												@PathVariable int currentPage,
												@PathVariable int cnt_per_page){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			List<Lesson> list = LPSService.findByPageStudentLesson(currentPage, cnt_per_page, studentId);
			map.put("status", 1);
			map.put("list", list);
		} catch (FindException e) {
			
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
		}
		
		return map;
		
	}
	
	@GetMapping("/mypage/myStudentLesson/selectByStudentAllLessonCnt/{studentId}")
	public Map<String, Object> selectByStudentAllLessonCnt(@PathVariable int studentId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			int count = LPSService.findByStudentAllLessonCnt(studentId);
			map.put("status", 1);
			map.put("list", count);
		} catch (FindException e) {
		
			e.printStackTrace();
			map.put("status", 1);
			map.put("list", e.getMessage());
		}
		
		return map;
		
	}
	
	
	
}
