package com.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.service.LessonReviewService;
import com.my.vo.LessonReview;
import com.my.vo.PageGroupBean;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class LessonReviewController {
	@Autowired
	LessonReviewService lessonReviewService;

	@GetMapping(value = { "review/list/{lesson_id}", 
						  "review/list/{lesson_id}/{currentPage}" })
	public Map<String, Object> list(@PathVariable("lesson_id") int lesson_id,
									@PathVariable("currentPage") Optional<Integer> optCurrentPage) throws Exception {
		Map<String, Object> map = new HashMap<>();
		int currentPage = 1;
		int cnt_per_page = 10;
		int totalCnt = lessonReviewService.findCnt(lesson_id);
		List<LessonReview> list = null;
		PageGroupBean<LessonReview> pgb = null;
		
		if(optCurrentPage.isPresent()) {
			currentPage = optCurrentPage.get();
		}
		list = lessonReviewService.findPerPage(lesson_id, currentPage, cnt_per_page);
		pgb = new PageGroupBean<LessonReview>(totalCnt, currentPage, list);
		map.put("status", 1);
		map.put("pgb", pgb);
		return map;
	}
	
	@PostMapping(value= "review/add")
	public Map<String, Object> add(HttpSession session, @RequestBody LessonReview lessonReview) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int student_id = 0;
		
		Integer wrapperStudent_id = (Integer)session.getAttribute("loginInfo");
		if(wrapperStudent_id != null) {
			student_id = wrapperStudent_id;
			lessonReviewService.add(lessonReview);
			map.put("stauts", 1);
		}else {
			log.debug("로그인 안 함");
			map.put("status", 0);
		}
		return map;
	}

	@GetMapping(value = { "admin/review/list", 
						  "admin/review/list/{currentPage}",
						  "admin/review/list/{currentPage}/{word}" }, 
				produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> adminList(@PathVariable("currentPage") Optional<Integer> optCurrentPage,
										 @PathVariable("word") Optional<String> optWord, Authentication auth) throws Exception {
		int currentPage = 1;
		int cnt_per_page = 10;
		int totalCnt = lessonReviewService.findCnt();

		List<LessonReview> list = null;
		Map<String, Object> map = new HashMap<>();
		PageGroupBean<LessonReview> pgb = null;

		if (auth != null) {
			if (optCurrentPage.isPresent()) {
				currentPage = optCurrentPage.get();
			}
			if (optWord.isPresent()) {
				String word = optWord.get();
				list = lessonReviewService.findByLessonTitleORStudentNamePerPage(word, currentPage, cnt_per_page);
				totalCnt = lessonReviewService.findCnt(word);
			} else {
				list = lessonReviewService.findPerPage(currentPage, cnt_per_page);
			}
			pgb = new PageGroupBean<LessonReview>(totalCnt, currentPage, list);
			map.put("pgb", pgb);
			map.put("status", 1);
			log.debug(pgb);
		} else {
			map.put("status", -1);
			log.warn("adminList" + auth);
		}
		return map;
	}

	@DeleteMapping(value = "admin/review/delete/{review_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> adminRemove(@PathVariable("review_id") int review_id, Authentication auth)
			throws Exception {

		Map<String, Object> map = new HashMap<>();
		if (auth != null) {
			lessonReviewService.remove(review_id);
			map.put("status", 1);
		} else {
			map.put("status", -1);
			log.warn("adminRemove" + auth);
		}
		return map;
	}

}