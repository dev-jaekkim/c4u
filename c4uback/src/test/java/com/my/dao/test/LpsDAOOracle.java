package com.my.dao.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.commons.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.dao.LPSDAO;
import com.my.dao.LessonDAO;
import com.my.dao.StudentDAO;
import com.my.exception.FindException;
import com.my.vo.Lesson;
import com.my.vo.Student;

//Spring용 단위테스트
//@WebAppConfiguration //JUnit5인 경우 
@RunWith(SpringJUnit4ClassRunner.class) // junit4

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class LpsDAOOracle {

	@Autowired
	private LPSDAO lpsdao;

	
	//@Test
	public void selectBylessonId() {
		int lesson_id = 7;
		try {
			List<Student> list = lpsdao.selectByStudentInformation(lesson_id);
			assertNotNull(list);
		} catch (FindException e) {
		
			e.printStackTrace();
		} 
		
	}
	
	//@Test
	public void selectByStudentAllLessonCnt() {
		int student_id = 26;
		try {
			int list = lpsdao.selectByStudentAllLessonCnt(student_id);
			assertNotNull(list);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void selectByPage() {
		int student_id = 26;
		int currentPage = 1;     //현재페이지
		int cnt_per_page = 10;   //페이지별 보여줄 목록 수 
		
		try {
			List<Lesson> list = lpsdao.selectByPageStudentLesson(currentPage, cnt_per_page, student_id);
			assertNotNull(list);
		} catch (FindException e) {
		
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
