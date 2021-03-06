package com.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.my.vo.Notice;
import com.my.vo.PageGroupBean;


import lombok.extern.log4j.Log4j;

@CrossOrigin("*")  
@RestController
@Log4j
public class AdminLessonController {
	@Autowired
	private AdminLessonService service;

	// 심사 승인
	@PutMapping("/admin/lesson/modifylessonstatus/{lessonId}")
	public Map<String, Object> adminModifyLesson(@PathVariable int lessonId
	// ,Authentication auth
	) throws Exception {
		log.info("강좌번호" + lessonId);
		Map<String, Object> map = new HashMap<>();
		// if(auth != null) {
		service.modifyLesson(lessonId);
		map.put("status", 1);

		// }else {
		// log.warn("adminDetail"+auth);
		// map.put("status", 9);
		// }
		//		}else {
		//			log.warn("adminDetail"+auth);
		//			map.put("status", -9);
		//		}
		return map;
	}

	// 심사 거절
	@PostMapping("/admin/lesson/insertpenaltystatus")
	public Map<String, Object> insertLessonPenaltyStatus(@RequestBody LessonPenaltyStatus lessonPs) throws Exception {
		log.info(lessonPs);
		Map<String, Object> map = new HashMap<>();
		service.addPenaltyStatus(lessonPs);
		map.put("status", 1);
		return map;
	}

	@GetMapping("/admin/lesson/lessonpenaltyall")
	public Map<String, Object> findLessonPenaltyAll() throws Exception {
		Map<String, Object> map = new HashMap<>();
		List<LessonPenalty> list = service.findLessonPenaltyAll();
		map.put("lessonPenalty", list);
		map.put("status", 1);
		return map;
	}

	// 해당 강좌의 lessonps 조회
	@GetMapping("/admin/lesson/lessonps/{lessonId}")
	public Map<String, Object> findLessonPs(@PathVariable int lessonId) throws Exception {
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
	
	

	

	@GetMapping(value = { "/admin/Evaluationlist", 
						  "/admin/Evaluationlist/{currentPage}",
						  "/admin/Evaluationlist/{currentPage}/{word}"}, 
						  produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> selectLessonList(@PathVariable("currentPage") Optional<Integer> optCurrentPage,
												@PathVariable("word") Optional<String> optWord, 
												Authentication auth) throws Exception {
		String word = null;
		int currentPage = 1;
		List<Lesson> list = null;
		Map<String, Object> map = new HashMap<>();
		int cnt_per_page = 10;
		int totalCnt = service.findCnt();
		PageGroupBean<Lesson> pgb = null;

		if (auth!=null) {
			if (optCurrentPage.isPresent()) {
				currentPage = optCurrentPage.get();
				log.info(word);
			}

			if (optWord.isPresent()) { //검색어 입력된경우
				word = optWord.get();
				list = service.findLessonEvaluationList(currentPage, cnt_per_page, word);
				totalCnt = service.findCnt(word);
				log.info("front 검색입력" + word);
			}else { //검색어 입력안된경우
				list = service.findPerPage(currentPage, cnt_per_page);
				//totalCnt = service.findCnt();
				//totalCnt = 20;
				
			}
			log.info("totalCnt =" + totalCnt +", list ="+ list );
	
			pgb = new PageGroupBean<>(totalCnt, currentPage, list);
			log.debug(pgb);
			map.put("pgb", pgb);
			map.put("status", 1);
		} else {
			log.warn("adminList" + auth);
			map.put("status", 9);
		}
		return map;
	}
}
