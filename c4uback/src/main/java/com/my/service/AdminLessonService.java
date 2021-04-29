package com.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.AdminLessonDAO;
import com.my.exception.AddException;
import com.my.exception.ModifyException;
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

}
