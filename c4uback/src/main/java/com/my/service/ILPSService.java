package com.my.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.LPS;
import com.my.vo.Lesson;
import com.my.vo.Student;


public interface ILPSService {
	
	List<Lesson> findByStudentId(int studentId) throws FindException;
	
	LPS findByStudentLessonId(int lessonId, int studentId) throws FindException;
	
	LPS findByLPSId(int lpsId) throws FindException;
	
	List<LPS> findByLessonId(int lessonId) throws FindException;

	void add(int lessonId, int studentId) throws AddException;
	
	int findAllCount(int studentId, int lessonStatus) throws FindException;
	
	int findAllCount (int studentId) throws FindException;
	
	List<Lesson> findByPage(int currPage, int dataPerPage, int studentId) throws FindException;
	
	List<Lesson> findByPageStudentLesson(int currentPage, int cnt_per_page, int studentId) throws FindException;
	
	public List<Lesson> findByPage(int currPage, int dataPerPage, int studentId, int lessonStatus) throws FindException;
	
	public List<Student> findByStudentInformation(int lessonId) throws FindException;
	
	public int findByStudentAllLessonCnt(int studentId) throws FindException;
}
