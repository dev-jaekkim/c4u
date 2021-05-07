package com.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.my.service.CategoryService;
import com.my.vo.Category;
import com.my.vo.Lesson;

import lombok.extern.log4j.Log4j;
@CrossOrigin("*")  	
@RestController
@Log4j
public class CategoryController {
	@Autowired
	private CategoryService service;
	
	@GetMapping("/lesson/categoryall")
	public Map<String, Object> findCategoryAll() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Category> list = service.findAll();
		map.put("category", list);
		map.put("status", 1);
		return map;
	}
	
	@GetMapping("/lesson/category/{categoryId}")
	public Map<String, Object> findByCategoryId(@PathVariable("categoryId") int categoryId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Lesson> list = service.findById(categoryId);
		map.put("list", list);
		map.put("status", 1);
		return map;
	}
}
