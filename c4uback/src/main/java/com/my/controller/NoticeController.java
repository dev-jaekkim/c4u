package com.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.service.NoticeService;
import com.my.vo.Notice;
import com.my.vo.PageGroupBean;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@CrossOrigin("*")
public class NoticeController {

	@Autowired
	private NoticeService service;

	@GetMapping(value = { "/notice/list", 
						  "/notice/list/{currentPage}"})
	public Map<String, Object> list(@PathVariable("currentPage") Optional<Integer> optCurrentPage) throws Exception {
		int currentPage = 1;
		List<Notice> list = null;
		Map<String, Object> map = new HashMap<>();
		int cnt_per_page = 10;
		int totalCnt = service.findCnt();
		PageGroupBean<Notice> pgb = null;

		if (optCurrentPage.isPresent()) {
			currentPage = optCurrentPage.get(); // AsInt();
		}
		
		list = service.findPerPage(currentPage, cnt_per_page);
		pgb = new PageGroupBean<>(totalCnt, currentPage, list);
		log.debug(pgb);
		map.put("pgb", pgb);
		map.put("status", 1);
		return map;
	}

	@GetMapping("/notice/detail/{notice_id}")
	public Map<String, Object> detail(@PathVariable("notice_id") int notice_id) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Notice notice = service.findById(notice_id);
		log.debug(notice);
		map.put("notice", notice);
		map.put("status", 1);	
		return map;
	}

	@GetMapping(value = {"/admin/notice/list", 
						 "/admin/notice/list/{currentPage}",
						 "/admin/notice/list/{currentPage}/{word}"}, 
						 produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> adminList(@PathVariable("currentPage") Optional<Integer> optCurrentPage,
										 @PathVariable("word") Optional<String> optWord,
										 Authentication auth) throws Exception {
		String word = null;
		int currentPage = 1;
		List<Notice> list = null;
		Map<String, Object> map = new HashMap<>();
		int cnt_per_page = 10;
		int totalCnt = service.findCnt();
		PageGroupBean<Notice> pgb = null;
		if(auth!=null) {
			if (optCurrentPage.isPresent()) {
				currentPage = optCurrentPage.get(); // AsInt();
				log.info("front에서 word 없고 보냄 word: "+word);
			}
			if (optWord.isPresent()) {
				word = optWord.get();
				list = service.findByTitlePerPage(word, currentPage, cnt_per_page);
				totalCnt = service.findCnt(word);
				log.info("front에서 word포함해서 보냄 word: "+word);
			} else {
				list = service.findPerPage(currentPage, cnt_per_page);
			}
			pgb = new PageGroupBean<>(totalCnt, currentPage, list);
			log.debug(pgb);
			map.put("pgb", pgb);
			map.put("status", 1);
		}else {
			log.warn("adminList"+auth);
			map.put("status",-9);
		}
		return map;
	}

	@GetMapping(value="/admin/notice/detail/{notice_id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> adminDetail(@PathVariable("notice_id") int notice_id,
										   Authentication auth) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if(auth != null) {
			Notice notice = service.findById(notice_id);
			log.debug(notice);
			map.put("notice", notice);
			map.put("status", 1);
		}else {
			log.warn("adminDetail"+auth);
			map.put("status",-9);
		}
		return map;
	}

	@PutMapping(value="/admin/notice/modify", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> adminModify(@RequestBody Notice notice,
											Authentication auth) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if(auth != null) {
		Notice modifiedNotice = service.modify(notice);
		log.debug(modifiedNotice);
		map.put("notice", modifiedNotice);
		map.put("status", 1);
		}else {
			log.warn("adminModify"+auth);
			map.put("status",-9);
		}
		return map;
	}

	@DeleteMapping(value="/admin/notice/delete/{notice_id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> adminDelete(@PathVariable int notice_id,
										   Authentication auth) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if(auth != null) {
			service.remove(notice_id);
			log.debug(notice_id);
			map.put("status", 1);
		}else {
			log.warn("adminDelete"+auth);
			map.put("status",-9);
		}
		return map;
	}

	@PostMapping(value="/admin/notice/write", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> adminWrite(@RequestBody Notice notice,
										  Authentication auth) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if(auth != null) {
			service.add(notice);
			log.debug(notice);
			map.put("status", 1);
		}else {
			log.warn("adminDelete"+auth);
			map.put("status",-9);
		}
		return map;
	}
}
