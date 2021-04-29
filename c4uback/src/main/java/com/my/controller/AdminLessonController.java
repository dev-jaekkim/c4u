//package com.my.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.my.exception.AddException;
//import com.my.exception.ModifyException;
//import com.my.service.AdminLessonService;
//import com.my.vo.LessonPenaltyStatus;
//
//@Controller
//public class AdminLessonController {
//	@Autowired
//	private AdminLessonService service;
//
//	//심사 승인
//	//test!!!
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@PutMapping("/admin/lesson/modifylessonstatus")
//	public void adminModifyLesson(@PathVariable int lessonId) {
//		Map<String, Object> map = new HashMap<>();
//		try {
//			service.modifyLesson(lessonId);
//			map.put("status", 1);
//		} catch (ModifyException e) {
//			e.printStackTrace();
//			map.put("status", -1);
//			map.put("msg", e.getMessage());
//		}
//	}
//	
//	//심사 거절 
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@PostMapping("/insertpenaltystatus")
//	public void insertLessonPenaltyStatus (@RequestBody LessonPenaltyStatus lessonPs) {
//		try {
//			service.addPenaltyStatus(lessonPs);
//			
//			
//			
//			
//		} catch (AddException e) {
//			e.printStackTrace();
//		}
//	}
//}
