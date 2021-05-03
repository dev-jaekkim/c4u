package com.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.RemoveException;
import com.my.service.StudentService;
import com.my.vo.PageGroupBean;
import com.my.vo.Penalty;
import com.my.vo.PenaltyStatus;
import com.my.vo.Student;

import lombok.extern.log4j.Log4j;
@CrossOrigin("*")
@RestController
@Log4j
public class StudentController {
	@Autowired
	private StudentService service;

	//마무리하기
	//	public void add (Student s) throws AddException{
	//		Map<String, Object> map = new HashMap<>();
	//		try {
	//			service.add(s);
	//			map.put("status", 1);
	//		} catch (AddException e) {
	//			e.printStackTrace();
	//			map.put("status", -1);
	//			map.put("msg", e.getMessage());
	//		}
	//	
	//	}

	//front 페이징
	@GetMapping(value={"/admin/studentlist/{currentPage}", "/admin/studentlist/{currentPage}/{word}"})
	public Map<String, Object> adminStudentList(@PathVariable("currentPage") int currentPage,
										@PathVariable("word") Optional<String> word
//										,
//										Authentication auth
										) throws FindException {
		log.info(word);
		List<Student> list;
		int cnt_per_page = 10;
		Map<String, Object> map = new HashMap<>();
		
//		if(auth != null) {
			if(word.isPresent()) { 
				String search = word.get(); //optional로 받은거  get해서 String으로 
				list = service.findStudentList(currentPage, cnt_per_page, search);
				int totalCnt = service.findCnt();
				PageGroupBean<Student> pgb = new PageGroupBean<>(totalCnt, currentPage, list);
				pgb.setList(list);
				map.put("page_group_bean", pgb);
				map.put("status", 1);
			}else { 
				list = service.findStudentList(currentPage, cnt_per_page, null); 
				int totalCnt = service.findCnt();
				PageGroupBean<Student> pgb = new PageGroupBean<>(totalCnt, currentPage, list);
				pgb.setList(list);
				map.put("page_group_bean", pgb);
				map.put("status", 1);
			}
			return map;
//		}else {
//			log.warn("adminDetail"+auth);
//			map.put("status", 9);
//		}
//		return map;

	}
	
	@GetMapping("/admin/penaltylist")
	public Map<String, Object> findPenaltyAll() throws FindException{
		Map<String, Object> map = new HashMap<>();
		try {
			List<Penalty> list = service.findPenaltyAll();
			map.put("penalty", list);
			map.put("status", 1);
			return map;
		}catch(Exception e) {
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
			return map;
		}
	}
	

	@PostMapping(value="/admin/addpenaltystatus")
	public Map<String, Object> addPenaltyStatus (@RequestBody PenaltyStatus ps) throws AddException {
		Map<String, Object> map = new HashMap<>();
		try {
			service.addPenaltyStatus(ps);
			map.put("status", 1);
			return map;
		}catch(Exception e) {
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
			return map;
		}
	}
	
	@PutMapping("/admin/modifystatus/{student_id}")
	public Map<String, Object> modifyStatus(@PathVariable int student_id) throws RemoveException{
		Map<String, Object> map = new HashMap<>();
		try {
			service.modifyStatus(student_id);
			map.put("status", 1);
			return map;
		}catch(Exception e) {
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
			return map;
		}
	}
	
	@GetMapping("/admin/findbyid/{word}")
	public Map<String, Object> findByStudentId (@PathVariable("word") int student_id) throws FindException{
		log.info(student_id);
		Map<String, Object> map = new HashMap<>();
		try {
			Student student = service.findById(student_id);
			map.put("student", student);
			map.put("status", 1);
			return map;
		}catch(Exception e) {
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
			return map;
		}

	}
	
}
