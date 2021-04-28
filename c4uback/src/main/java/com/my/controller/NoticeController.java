package com.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@GetMapping(value={"/notice/list",
					   "/notice/list/{currentPage}", 
					   "/admin/notice/list",
					   "/admin/notice/list/{currentPage}", 
					   "/admin/notice/list/{currentPage}/{notice_title}"})
	public Map<String, Object> list(@PathVariable("currentPage") Optional<Integer> optCurrentPage,
									@PathVariable("notice_title") Optional<String>optTitle) throws Exception{
		String notice_title = null;
		int currentPage = 1;
		List<Notice> list = null;
		Map<String, Object> map = new HashMap<>();
		int cnt_per_page = 10;
		int totalCnt = service.findCnt();
		PageGroupBean<Notice> pgb = null;
		
		if(optCurrentPage.isPresent()) {
			currentPage = optCurrentPage.get(); //AsInt();
		}
		if(optTitle.isPresent()) {
				notice_title = optTitle.get();
				list = service.findByTitlePerPage(notice_title, currentPage, cnt_per_page);
		}else {
				list = service.findPerPage(currentPage, cnt_per_page);
		}
		pgb = new PageGroupBean<>(totalCnt, currentPage, list);
		log.debug(pgb);
		map.put("pgb", pgb);
		map.put("status", 1);
		return map;
	}
	
	@GetMapping(value = {"/notice/detail/{notice_id}", 
						 "/admin/notice/detail/{notice_id}"})
	public Map<String, Object>detail(@PathVariable("notice_id") int notice_id) throws Exception{
		Map<String, Object> map = new HashMap<>();
		Notice notice = service.findById(notice_id);
		log.debug(notice);
		map.put("notice", notice);
		map.put("status", 1);
		return map;
	}
//	@PreAuthorize("permitAll")
	@PutMapping("/admin/notice/modify")
	public Map<String, Object> adminModify(@RequestBody Notice notice) throws Exception{
		Map<String, Object> map = new HashMap<>();
		Notice modifiedNotice = service.modify(notice);
		log.debug(modifiedNotice);
		map.put("notice", modifiedNotice);
		map.put("status", 1);
		return map;
	}
	
	@DeleteMapping("/admin/notice/delete/{notice_id}")
	public Map<String, Object> adminDelete(@PathVariable int notice_id)throws Exception{
		Map<String, Object> map = new HashMap<>();
		service.remove(notice_id);
		log.debug(notice_id);
		map.put("status", 1);		
		return map;
	}
	
	@PostMapping("/admin/notice/write")
	public Map<String, Object> adminWrite(@RequestBody Notice notice)throws Exception{
		Map<String, Object> map = new HashMap<>();
		service.add(notice);
		log.debug(notice);
		map.put("status", 1);
		return map;
	}
}
