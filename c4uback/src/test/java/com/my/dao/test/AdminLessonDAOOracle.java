package com.my.dao.test;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.dao.AdminLessonDAO;
import com.my.dao.LessonDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.vo.Lesson;
import com.my.vo.LessonPenalty;
import com.my.vo.LessonPenaltyStatus;
@RunWith(SpringJUnit4ClassRunner.class) //Junit4인 경우

//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class AdminLessonDAOOracle {

	@Autowired
	private AdminLessonDAO dao;
	@Autowired
	private LessonDAO ldao;

	//lesson selectById 한 번 더 확인
	//@Test
	public void insertPenaltyStatus() throws FindException,AddException, ParseException {
		//심사할 강좌 선택
		int lesson_id =21;
		Lesson lesson = ldao.selectById(lesson_id);

		//lessonps
		int psId = 1;
		LessonPenalty lessonPenalty = new LessonPenalty(psId,"내용불충분");

		String from = "2021-04-18";
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		Date lessonps_dt = fm.parse(from);

		LessonPenaltyStatus lessonps = new LessonPenaltyStatus(1, lesson,lessonPenalty, lessonps_dt);
		dao.insertPenaltyStatus(lessonps);
	}

	//@Test 
	public void updateLesson() throws FindException, ModifyException{
		int lessonId = 35;
		dao.updateLesson(lessonId);
	}
	



	//@Test
	public void selectLessonPenaltyAll () throws FindException{
		List<LessonPenalty> list = dao.selectLessonPenaltyAll();
		int expListSize = 3;
		assertEquals(expListSize, list.size());
	}
	
//	@Test
//	public void selectLessonDetail() throws FindException{
//		int lessonId = 37;
//		Lesson lesson = dao.selectLessonDetail(lessonId);
//		int expTeacherId = 18;
//		assertEquals(expTeacherId, lesson.getTeacher().getStudentId());
//	}
	//@Test
	public void selectLessonPs() throws FindException{
		int lessonId= 37;
		List<LessonPenalty> lessonps = dao.selectLessonPs(lessonId);
		int expSize = 3;
		assertEquals(expSize, lessonps.size());
	}
	
<<<<<<< HEAD
	//@Test
	public void adminselectLessonEvaluationList() {
=======
//	@Test
	public void adminSelectByPage() {
>>>>>>> 539f10c5a26c3e2dc3575ad47c5b0e4309687084
		
		String word = "a";
		int currentPage = 1;
		int cnt_per_page = 20;
		int expsize = 8;
		
		try {
			List<Lesson> list = dao.selectLessonEvaluationList(currentPage, cnt_per_page, word);
			assertTrue(list.size() == expsize);
		} catch (FindException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void adminSelectByPage() {
		
		String word = "복";
		int currentPage = 1;
		int cnt_per_page = 10;
		int expsize = 4;
		
		try {
			List<Lesson> list = dao.selectLessonList(currentPage, cnt_per_page, word);
			assertTrue(list.size() == expsize );
		} catch (FindException e) {
			e.printStackTrace();
		}
		
	}
}
