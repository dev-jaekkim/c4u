package com.my.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.dao.CategoryDAO;
import com.my.dao.LessonDAO;
import com.my.exception.FindException;
import com.my.vo.Category;
import com.my.vo.Lesson;
@RunWith(SpringJUnit4ClassRunner.class) //Juni4인 경우

//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CategoryDAOOracle {

	@Autowired
	private CategoryDAO dao;
	
//	@Test
	public void selectAll() throws FindException {
		List<Category> list = dao.selectAll();
		int expListSize =18;
		assertTrue(expListSize == list.size());
	}
	
//	@Test
	public void selectById() throws FindException {
		int categoryId = 101;
		List<Lesson> list = dao.selectById(categoryId);
		int expListSize = 1;
		assertEquals(expListSize, list.size());
	}
}
