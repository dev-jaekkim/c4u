package com.my.dao.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.dao.LessonReviewDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.RemoveException;
import com.my.vo.LPS;
import com.my.vo.LessonReview;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

@Log4j
public class LessonReviewDAOOracle {
	@Autowired
	private LessonReviewDAO dao;
	
//	@Test
	public void adminSelectPerPage() throws FindException{
		int currentPage = 1;
		int cnt_per_page = 3;
		
		List<LessonReview> list = dao.selectPerPage(currentPage, cnt_per_page);
		int expListSize = 3;
		assertTrue(expListSize == list.size());
		for(LessonReview l : list) {
			log.debug(l);
		}
	}
	
//	@Test
	public void userSelectPerPage() throws FindException{
		int currentPage = 1;
		int cnt_per_page = 3;
		int lessonId = 22;
		
		List<LessonReview> list = dao.selectPerPage(lessonId, currentPage, cnt_per_page);
		int expListSize = 3;
		assertTrue(expListSize == list.size());
		for(LessonReview l : list) {
			log.debug(l);
		}
	}
	
//	@Test
	public void adminSelectCnt() throws FindException{
		int cnt = dao.selectCnt();
		int expCnt = 11;
		assertTrue(cnt == expCnt);
	}
	
	
//	@Test
	public void userSelectCnt() throws FindException{
		int lessonId = 22;
		int cnt = dao.selectCnt(lessonId);
		int expCnt = 3;
		assertTrue(cnt == expCnt);
	}
	
//	@Test
	public void selectByLessonTitleORStudentNamePerPage() throws FindException{
		String word = "rma";
		int currentPage = 1;
		int cnt_per_page = 3;
		
		List<LessonReview> list = dao.selectByLessonTitleORStudentNamePerPage(word, currentPage, cnt_per_page);
		int expSize = 1;
		assertTrue(expSize == list.size());
	}
	
//	@Test
	public void insert() throws AddException, FindException{
		LessonReview review = new LessonReview();
		LPS lps = new LPS();
		int recommend = 0;
		String reviewContent = "명강의!";
		int lpsId = 109;
		
		lps.setLPSId(lpsId);
		review.setLps(lps);
		review.setRecommend(recommend);
		review.setReviewContent(reviewContent);
		dao.insert(review);
	}
	
//	@Test
	public void delete() throws RemoveException, FindException{
		int review_id = 12;
		dao.delete(review_id);
	}
}