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
	public List<LessonPenalty> findLessonPs(int lessonId) throws FindException {
		return dao.selectLessonPs(lessonId);
	}

//	@Override
//	public Lesson findDetailByLessonId(int lessonId) throws FindException {
//		return dao.selectLessonDetail(lessonId);
//	}
	
	

}
