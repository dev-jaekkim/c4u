package com.my.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.dao.StudentDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.vo.Penalty;
import com.my.vo.PenaltyStatus;
import com.my.vo.Student;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class) //Juni4인 경우

//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class StudentDAOOracle {

	@Autowired
	private StudentDAO dao;

	//@Test
	public void selectStudentList() throws FindException {
		//검색어 입력한 경우 
		//String word ="abc";
		//List<Student> list = dao.selectStudentList(1, 10, word);	
//		assertNotNull(list);
//		assertEquals(list.get(0).getPenaltyStatus().size(), 1);
		//assertEquals(list.size(), 0);
		
		//회원 전체 조회
		List<Student> list = dao.selectStudentList(1, 10, null);
		int expStudent = 22;
		assertNotNull(list);
		assertEquals(expStudent, list.size());
	}
	
	//@Test
	public void selectCnt() throws FindException{
		int expStudent = 22;
		int studentSize = dao.selectCnt();
		assertEquals(expStudent, studentSize);
	}

	//@Test
	public void selectById() throws FindException {
		int student_id = 20;
		String expEmail = "alountj@chron.com";
		String expName = "Choi Jun Hee";
		Student s = dao.selectById(student_id);
		assertNotNull(s);
		assertEquals(expEmail, s.getStudentEmail());
		assertEquals(expName, s.getStudentName());
			
	}
	
	//@Test
	public void selectByEmail() throws FindException{
		String studentEmail = "alountj@chron.com";
		int expStudentId = 20;
		Student s = dao.selectByEmail(studentEmail);
		assertNotNull(s);
		assertEquals(expStudentId, s.getStudentId());
	}
	
	//@Test
	public void selectByName() throws FindException{
		String student_name = "Choi Jun Hee";
		String expEmail = "alountj@chron.com";
		Student s = dao.selectByName(student_name);
		assertNotNull(s);
		assertEquals(expEmail, s.getStudentEmail());
	}
	
	//@Test
	public void selectByPwd() throws FindException{
		String inputPwd = "b222";
		Student s = dao.selectByPwd(inputPwd);
		String expEmail = "abc@gmail.com";
		String expName = "abc";
		String expPhone ="010-3333-3333";
		assertEquals(expEmail, s.getStudentEmail());
		assertEquals(expName, s.getStudentName());
		assertEquals(expPhone, s.getStudentPhone());
	}
	
	//@Test
	public void selectPenalty() throws FindException {
		List<Penalty> list = dao.selectPenaltyAll();
		int expListSize = 4;
		assertEquals(expListSize, list.size());
	}
	
	//@Test
	public void insert() throws AddException, FindException{
		int studentId = 22;
		String studentEmail = "admin@admin.com";
		String studentPwd = "123!@";
		String studentName = "abc123";
		String studentPhone = "010-4443-3333";
		int studentStatus = 1;
		Student s = new Student(studentId, studentEmail, studentPwd, studentName, studentPhone, studentStatus);
		dao.insert(s);	
	}
	
	//@Test
	public void update() throws FindException, ModifyException{
		String studentEmail = "abc@gmail.com";
		String studentName ="abc";
		String inputPwd ="a222";
		String studentPhone = "010-2222-2222";
		Student s = dao.selectByEmail(studentEmail);
	
		//비밀번호, 전화번호 변경 
//		String studentPwd = "a222";
//		String studentPhone = "010-2222-2222";
//		s.setStudentPwd(studentPwd);
//		s.setStudentPhone(studentPhone);
//		dao.update(s, inputPwd);
//		assertEquals(studentPwd, s.getStudentPwd());
//		assertEquals(studentName, s.getStudentName());
//		assertEquals(studentPhone, s.getStudentPhone());
//		assertEquals(studentEmail, s.getStudentEmail());
		
		//비밀번호만 변경
		String studentPwd = "b222";
//		s.setStudentPwd(studentPwd);
//		dao.update(s, inputPwd);
//		assertEquals(studentPwd, s.getStudentPwd());
//		assertEquals(studentName, s.getStudentName());
//		assertEquals(studentPhone, s.getStudentPhone());
//		assertEquals(studentEmail, s.getStudentEmail());
//		
		//번호만 변경
		studentPhone = "010-4444-3333";
		inputPwd = "b222";
		s.setStudentPhone(studentPhone);
		dao.update(s, inputPwd);
		assertEquals(studentPwd, s.getStudentPwd());
		assertEquals(studentName, s.getStudentName());
		assertEquals(studentPhone, s.getStudentPhone());
		assertEquals(studentEmail, s.getStudentEmail());
	}
	
	//@Test
	public void insertPenaltyStatus() throws AddException, FindException, ParseException {
		int ps_id = 5;
		Student student = dao.selectById(21);
	
		Penalty penalty = new Penalty();
		penalty.setPenaltyId(1);
		penalty.setPenaltyContent("욕설 사용");
		
		String from = "2021-04-17";
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		Date ps_date = fm.parse(from);
		PenaltyStatus ps = new PenaltyStatus (ps_id, student, penalty, ps_date);
		
		dao.insertPenaltyStatus(ps);
		
		int expId = 21;
		int expPenaltyId = 1;
		assertEquals(expId, ps.getStudent().getStudentId());
		assertEquals(expPenaltyId, ps.getPenalty().getPenaltyId());
	}
	
	//@Test
	public void updateStatus() throws FindException, ModifyException{
		Student s = dao.selectById(21);
		dao.updateStatus(s.getStudentId());
		String expEmail = "abc@gmail.com";
		assertEquals(21, s.getStudentId());
		assertEquals(expEmail, s.getStudentEmail());
	}
	
	//@Test
	public void updateAdminPwd() throws FindException, ModifyException {
		String certifyPwd = "Tlvhdbdjemals1";
		Student student = dao.selectByPwd(certifyPwd); //비밀번호 확인
		String studentPwd = "DjemalsTlvhdb1";
		student.setStudentPwd(studentPwd);
		dao.updateAdminPwd(student, certifyPwd);
		assertEquals("admin@admin.com", student.getStudentEmail()); //관리자 객체 확인
		assertEquals(studentPwd, student.getStudentPwd());
		
	}
}
