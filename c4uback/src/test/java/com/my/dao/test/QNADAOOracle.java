package com.my.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.dao.QNADAO;
import com.my.dao.StudentDAO;
import com.my.dao.StudentDAOOracle;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.QNA;
import com.my.vo.Student;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

@Log4j
public class QNADAOOracle {
	@Autowired
	private QNADAO dao;
	
//	@Test
	public void selectPerPage() throws FindException{
		int currentPage = 1;
		int cnt_per_page = 3;
		
		List<QNA> list = dao.selectPerPage(currentPage, cnt_per_page);
		int expListSize = 3;
		assertTrue(expListSize == list.size());
		for(QNA q : list) {
			log.debug(q);
		}
	}
	
//	@Test
	public void selectCnt() throws FindException{
		int cnt = dao.selectCnt();
		int expcnt = 5;
		assertTrue(cnt == expcnt);
	}
	
//	@Test
	public void selectCntByWord() throws FindException{
		String word = "a";
		int cnt = dao.selectCnt(word);
		log.info(cnt);
		int expcnt = 2;
		assertTrue(cnt == expcnt);
	}
	
//	@Test 설계변경으로 사용하지 않음
//	public void selectByNamePerPage() throws FindException{
//		String student_name = "on";
//		int currentPage = 1;
//		int cnt_per_page = 5;
//		
//		List<QNA> list = dao.selectByNamePerPage(student_name, currentPage, cnt_per_page);
//		int expsize = 2;
//		assertTrue(list.size() == expsize);
//	}
	
//	@Test
	public void selectByIdAdmin() throws FindException{
		int qna_id = 1;
		QNA qna = dao.selectById(qna_id);
		assertTrue(qna.getQnaId() == qna_id);
	}
	
//	@Test
	public void selectByIdUser() throws FindException{
		int qna_id = 1;
		StudentDAO s = new StudentDAOOracle();
		Student student = s.selectById(5);
		
		QNA qna = dao.selectById(qna_id, student);
		assertTrue(qna.getQnaId() == qna_id);
	}
	
//	@Test
	public void selectByNameOrTitleOrContentPerPage() throws FindException{
		String word = "강좌";
		int currentPage = 1;
		int cnt_per_page = 10;
		int expsize = 5;
		
		List<QNA> list = dao.selectByNameOrTitleOrContentPerPage(word, currentPage, cnt_per_page);
		assertTrue(list.size()==expsize);
	}

//	@Test
	public void selectByStudentIdPerPage() throws FindException{
		int student_id = 1;
		int currentPage = 1;
		int cnt_per_page = 10;
		int expsize = 1;
		
		List<QNA> list = dao.selectByStudentIdPerPage(student_id, currentPage, cnt_per_page);
		assertTrue(list.size() == expsize);
	}
	
//	@Test
	public void insert() throws AddException, FindException{
		StudentDAO sdao = new StudentDAOOracle();
		QNA qna = new QNA();
		Student student = sdao.selectById(1);
		
		qna.setQnaTitle("문의0427");
		qna.setQnaContent("내용0427");
		qna.setStudent(student);
		
		dao.insert(qna);
	}
	
//	@Test
	public void update() throws ModifyException, FindException{
		String qna_comment="답변0427";
		QNA qna = dao.selectById(4);
		qna.setQnaComment(qna_comment);
		dao.update(qna);
		
		QNA newQna = dao.selectById(4);
		assertEquals(qna_comment, newQna.getQnaComment());
	}
	
//	@Test
	public void delete() throws RemoveException, FindException{
		int qna_id = dao.delete(1);
	}
}