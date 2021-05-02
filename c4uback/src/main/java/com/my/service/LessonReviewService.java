package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.LessonReviewDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.RemoveException;
import com.my.vo.LessonReview;

@Service
public class LessonReviewService implements ILessonReviewService {
	
	@Autowired
	private LessonReviewDAO dao;
	
	@Override
	public List<LessonReview> findPerPage(int currentPage, int cnt_per_page) throws FindException {
		return dao.selectPerPage(currentPage, cnt_per_page);
	}

	@Override
	public List<LessonReview> findPerPage(int lessonId, int currentPage, int cnt_per_page) throws FindException {
		return dao.selectPerPage(lessonId, currentPage, cnt_per_page);
	}

	@Override
	public int findCnt() throws FindException {
		return dao.selectCnt();
	}

	@Override
	public int findCnt(int lesson_id) throws FindException {
		return dao.selectCnt(lesson_id);
	}

	@Override
	public List<LessonReview> findByLessonTitleORStudentNamePerPage(String word, int currentPage, int cnt_per_page)
			throws FindException {
		return dao.selectByLessonTitleORStudentNamePerPage(word, currentPage, cnt_per_page);
	}

	@Override
	public void add(LessonReview lessonReview) throws AddException {
		dao.insert(lessonReview);
	}

	@Override
	public int remove(int review_id) throws RemoveException {
		return dao.delete(review_id);
	}

}
