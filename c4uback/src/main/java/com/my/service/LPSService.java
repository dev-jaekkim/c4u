package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.LPSDAO;
import com.my.dao.LPSDAOOracle;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.LPS;
import com.my.vo.Lesson;
import com.my.vo.Student;

@Service
public class LPSService implements ILPSService {
	@Autowired
	LPSDAO dao = new LPSDAOOracle();
	
	@Override
	public List<Lesson> findByStudentId(int studentId) throws FindException {
		return dao.selectByStudentId(studentId);
	}
	
	@Override
	public LPS findByStudentLessonId(int lessonId, int studentId) throws FindException {
		return dao.selectByStudentLessonId(lessonId, studentId);
	}

	@Override
	public LPS findByLPSId(int lpsId) throws FindException {
		return dao.selectByLPSId(lpsId);
	}
	
	@Override
	public List<LPS> findByLessonId(int lessonId) throws FindException {
		return dao.selectByLessonId(lessonId);
	}
	
	@Override
	public void add(int lessonId, int studentId) throws AddException {
		dao.insert(lessonId, studentId);
	}
	
	public int findAllCount(int studentId, int lessonStatus) throws FindException {
		return dao.selectAllCount(studentId, lessonStatus);
	}
	
	public List<Lesson> findByPage(int currPage, int dataPerPage, int studentId, int lessonStatus) throws FindException {
		return dao.selectByPage(currPage, dataPerPage, studentId, lessonStatus);
	}
	
	public int findAllCount(int studentId) throws FindException {
		return dao.selectAllCount(studentId);
	}
	
	@Override
	public List<Lesson> findByPage(int currPage, int dataPerPage, int studentId) throws FindException {
		return dao.selectByPage(currPage, dataPerPage, studentId);
	}

	@Override
	public List<Student> findByStudentInformation(int lessonId) throws FindException {
	
		return dao.selectByStudentInformation(lessonId);
	}

	@Override
	public List<Lesson> findByPageStudentLesson(int currentPage, int cnt_per_page, int studentId) throws FindException {
	
		return dao.selectByPageStudentLesson(currentPage, cnt_per_page, studentId);
	}

	@Override
	public int findByStudentAllLessonCnt(int studentId) throws FindException {
	
		return dao.selectByStudentAllLessonCnt(studentId);
	}
}
