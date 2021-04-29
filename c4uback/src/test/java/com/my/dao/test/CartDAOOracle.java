//cartdao test
package com.my.dao.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.dao.CartDAO;
import com.my.dao.LessonDAO;
import com.my.dao.StudentDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.RemoveException;
import com.my.vo.Cart;
import com.my.vo.Lesson;
import com.my.vo.Student;

//Spring용 단위테스트
//@WebAppConfiguration //JUnit5인 경우 
@RunWith(SpringJUnit4ClassRunner.class) // junit4

//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })

public class CartDAOOracle {
	@Autowired
	// @Qualifier("oracle")
	private CartDAO cartdao;

	//@Test
	public void selectById() {

		try {
			int student_id = 2;
			List<Lesson> list = cartdao.selectById(student_id);
			assertNotNull(list);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

    //@Test
	public void insert() {

		int cart_lesson_id = 39;
		int cart_student_id = 18;
		try {
			cartdao.insert(cart_lesson_id, cart_student_id);
		} catch (AddException e) {
			e.printStackTrace();
		}
	}

   //@Test
	public void delete() {
		int cart_lesson_id = 39;
		int cart_student_id = 18;
		try {
			cartdao.delete(cart_lesson_id, cart_student_id);
		} catch (RemoveException e) {
			e.printStackTrace();
		}
	}

    //@Test
	public void selectAllCount() {
		try {
			int list = cartdao.selectAllCount();
			assertNotNull(list);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void selectAllCountStudent() {
		int student_id = 2;
		try {
			int list = cartdao.selectAllCount(student_id);
			assertNotNull(list);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
}
