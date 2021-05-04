package com.my.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.vo.Lesson;
import com.my.vo.LessonPenalty;
import com.my.vo.LessonPenaltyStatus;

public interface IAdminLessonService {

	public void modifyLesson (int lessonId) throws ModifyException;
	
	public void addPenaltyStatus (LessonPenaltyStatus lessonPs) throws AddException;
	
	public List<LessonPenalty> findLessonPenaltyAll() throws FindException;

	List<Lesson> findLessonEvaluationList(int currentPage, int cnt_per_page, String word) throws FindException;

	List<Lesson> findLessonList(int currentPage, int cnt_per_page, String word) throws FindException;
	
//	public List<Lesson> findDetailByLessonId (int lessonId) throws FindException;
	
}
