package com.my.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@RunWith(SpringJUnit4ClassRunner.class) //Juni4인 경우

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
//	@Test
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
	public void updateLesson()throws ModifyException{
		int lessonId = 17;
		dao.updateLesson(lessonId);
	}
}
