package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.AdminLessonDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.vo.Lesson;
import com.my.vo.LessonPenalty;
import com.my.vo.LessonPenaltyStatus;
import com.my.vo.Notice;

@Service
public class AdminLessonService implements IAdminLessonService {
	@Autowired
	private AdminLessonDAO dao;

	@Override
	public void modifyLesson(int lessonId) throws ModifyException {
		dao.updateLesson(lessonId);
	}

	@Override
	public void addPenaltyStatus(LessonPenaltyStatus lessonPs) throws AddException {
		dao.insertPenaltyStatus(lessonPs);
	}

	@Override
	public List<LessonPenalty> findLessonPenaltyAll() throws FindException {
		return dao.selectLessonPenaltyAll();
	}
	
	
	@Override
	public List<Lesson> findLessonList(int currentPage, int cnt_per_page, String word) throws FindException{
		return dao.selectLessonList(currentPage, cnt_per_page, word);
		
	}
	

	@Override
	public List<LessonPenalty> findLessonPs(int lessonId) throws FindException {
		return dao.selectLessonPs(lessonId);
	}

//	@Override
//	public Lesson findDetailByLessonId(int lessonId) throws FindException {
//		return dao.selectLessonDetail(lessonId);
//	}
	@Override
	public int findCnt() throws FindException{
		return dao.selectCnt();	
	}

	@Override
	public List<Lesson> findLessonEvaluationList(int currentPage, int cnt_per_page,String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lesson> findLessonEvaluationList(String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int findCnt(String lesson_name) throws FindException{
		return dao.selectCnt(lesson_name);
	}

	@Override
	public List<Lesson> findPerPage(int currentPage, int cnt_per_page) throws FindException {
		
		return dao.selectPerPage(currentPage, cnt_per_page);
	}
	



	
	

}
