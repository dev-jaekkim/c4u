package com.my.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.RemoveException;
import com.my.exception.FindException;
import com.my.vo.Cart;
import com.my.vo.Lesson;

public interface ICartService {
	
	List<Lesson> findById(int studentId) throws FindException;
	
	Cart findById(int lessonId, int studentId) throws FindException;
	
	void add(int lessonId, int studentId) throws AddException;
	
	void remove(int lessonId, int studentId) throws RemoveException;

	int findAllCount() throws FindException;
	
	int findAllCount(int studentId) throws FindException;
	
	//List<Lesson> findByPage(int currPage, int dataPerPage,int studentId) throws FindException;
	
	List<Lesson> findByPage(int cnt_per_page, int currentPage,int studentId) throws FindException;
	
}
