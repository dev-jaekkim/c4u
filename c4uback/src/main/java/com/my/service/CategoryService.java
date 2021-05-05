package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.CategoryDAO;
import com.my.exception.FindException;
import com.my.vo.Category;
import com.my.vo.Lesson;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	CategoryDAO dao;
	
	@Override
	public List<Category> findAll() throws FindException {
		return dao.selectAll();
	}

	@Override
	public List<Lesson> findById(int categoryId) throws FindException {
		return dao.selectById(categoryId);
	}
}
