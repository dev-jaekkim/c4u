package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.LessonDAO;
import com.my.dao.LessonDAOOracle;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
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
	public List<Lesson> findByUnion(String union) throws FindException {
		return dao.selectByUnion(union);
	}
	
	@Override
	public int findCnt() throws FindException {
		return dao.selectCnt();
	}
	
	@Override
	public int findCnt(String word) throws FindException {
		return dao.selectCnt(word);
	}
	
	@Override
	public int findAllCount() throws FindException {
		return dao.selectCnt();
	}

	@Override
	public List<Lesson> findPerPage(int currentPage, int cnt_per_page) throws FindException {
		return dao.selectPerPage(currentPage, cnt_per_page);
	}
	
	@Override
	public List<Lesson> findByUnionPerPage(String union, int currentPage, int cnt_per_page) throws FindException {
		return dao.selectByUnionPerPage(union, currentPage, cnt_per_page);

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
	public int findByTeacherIdCnt(int studentId) throws FindException {
		return 0;
	}

	@Override
	public List<Lesson> findByLessonOpen(int studentId) throws FindException {
		return dao.selectByLessonOpen(studentId);
	}

	@Override
	public void add(Lesson lesson) throws AddException {
		dao.insert(lesson);
	}

	@Override
	public void modify(Lesson lesson) throws ModifyException {
		dao.update(lesson);
	}
}
