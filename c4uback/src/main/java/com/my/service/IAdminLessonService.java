package com.my.service;

import com.my.exception.AddException;
import com.my.exception.ModifyException;
import com.my.vo.LessonPenaltyStatus;

public interface IAdminLessonService {

	public void modifyLesson (int lessonId) throws ModifyException;
	
	public void addPenaltyStatus (LessonPenaltyStatus lessonPs) throws AddException;
	
}
