package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.LessonDAO;
import com.my.dao.LessonDAOOracle;
import com.my.exception.FindException;
import com.my.vo.Lesson;
@Service(value = "service")
public class LessonService implements ILessonService {
	@Autowired
	LessonDAO dao = new LessonDAOOracle();

	@Override
	public List<Lesson> findAll() throws FindException {
		List<Lesson> lAll = dao.selectAll();
		return lAll;
	}

	@Override
	public Lesson findById(int lessonId) throws FindException {
		return dao.selectById(lessonId);
	}

	@Override
	public List<Lesson> findUnion(String union) throws FindException {
		return dao.selectByUnion(union);
	}

	@Override
	public int findAllCount() throws FindException {
		return dao.selectCnt();
	}

	@Override
	public List<Lesson> findByPage(int currPage, int dataPerPage) throws FindException {
		return dao.selectByPage(currPage, dataPerPage);
	}

	@Override
	public List<Lesson> findBySearch(String word) throws FindException {
	
		return dao.selectBySearch(word);
	}

	@Override
	public List<Lesson> findByLessonStatus01234(int studentId, List<Integer> lesson_status) throws FindException {
	
		return dao.selectByLessonStatus01234(studentId, lesson_status);
	}

	@Override
	public List<Lesson> findByLessonOpen(int studentId) throws FindException {
		
		return dao.selectByLessonOpen(studentId);
	}
}
