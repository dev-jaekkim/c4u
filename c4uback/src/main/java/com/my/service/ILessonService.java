package com.my.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Lesson;

public interface ILessonService {
	List<Lesson> findAll() throws FindException;
	
	Lesson findById(int id) throws FindException;
	
	List<Lesson> findUnion(String union) throws FindException;
	
	int findAllCount() throws FindException;

	List<Lesson> findByPage(int currPage, int dataPerPage) throws FindException;
	
	List<Lesson> findBySearch(String word) throws FindException;
	
	List<Lesson> findByLessonStatus01234 (int studentId, List<Integer> lesson_status) throws FindException;

	List<Lesson> findByLessonOpen(int studentId) throws FindException;

	void add(Lesson lesson) throws AddException;
}
