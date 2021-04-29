package com.my.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.dao.NoticeDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Notice;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class NoticeDAOOracle {
	@Autowired
	private NoticeDAO dao;
	
//	@Autowired
//	public ExcpectedException expected;
	
//	@Test
	public void selectPage() throws FindException {
		
		int currentPage = 1;
		int cnt_per_page = 3;
		
		List<Notice> list= dao.selectPerPage(currentPage, cnt_per_page);
		int expListSize = 3;
		assertTrue(expListSize == list.size());
		for(Notice n : list) {
			log.debug(n);
		}
	}
	
	//@Test
	public void selectCnt() throws FindException{
		int cnt = dao.selectCnt();
		int expcnt = 32;
		assertTrue(cnt == expcnt);
	}
	
//	@Test
	public void selectTitlePerPage() throws FindException{
		String notice_title = "3"; 
		int currentPage = 1;
		int cnt_per_page = 10;

		List<Notice> list = dao.selectByTitlePerPage(notice_title, currentPage, cnt_per_page);
		int expListSize = 7;
		assertTrue(list.size()==expListSize);
	}
	
//	@Test
	public void selectById() throws FindException{
		int notice_id = 2;
		
		Notice notice = dao.selectById(notice_id);
		assertTrue(notice.getNoticeId()==notice_id);
	}
	
//	@Test
	public void update() throws ModifyException, FindException{
		String notice_title = "실화?";
		String notice_content = "ㅇㅇ실화";
		
		int notice_id = 2;
		
		Notice notice = dao.selectById(notice_id);
		notice.setNoticeTitle(notice_title);
		notice.setNoticeContent(notice_content);
		dao.update(notice);
		
		assertEquals(notice_title, notice.getNoticeTitle());
		assertEquals(notice_content, notice.getNoticeContent());
	}
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
//	@Test
	public void delete() throws RemoveException{
		int notice_id = 31;
		
		try {
		int delete_id = dao.delete(notice_id);
		assertEquals(notice_id, delete_id);
		}catch (RemoveException e){
			e.getMessage();
		}
		expected.expect(RemoveException.class);
		dao.delete(notice_id);
	}
	
	@Test
	public void insert() throws AddException{
		
		Notice notice = new Notice();
		
		String noticeTitle = "0419제목";
		String noticeContent = "0419내용";
		
		notice.setNoticeTitle(noticeTitle);
		notice.setNoticeContent(noticeContent);
		
		dao.insert(notice);
		
		assertEquals(noticeTitle,notice.getNoticeTitle());
		assertEquals(noticeContent,notice.getNoticeContent());
	}
}
