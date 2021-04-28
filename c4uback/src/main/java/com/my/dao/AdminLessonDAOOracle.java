package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Lesson;

public class AdminLessonDAOOracle implements AdminLessonDAO {

	@Override
	public List<Lesson> selectEvalHold(int lessonStatus) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lesson> selectLessonList(int currentPage, int cntPerPage, String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lesson selectLessonDetail(int lessonId) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void insertPenalty(int lessonId) throws AddException {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateLesson(int lessonId) throws ModifyException {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void deleteLesson(int lessonId) throws RemoveException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int selectCnt() throws FindException {
		// TODO Auto-generated method stub
		return 0;
	}

}
