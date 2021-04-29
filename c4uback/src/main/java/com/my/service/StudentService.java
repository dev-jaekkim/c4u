package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.StudentDAOOracle;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.vo.Penalty;
import com.my.vo.PenaltyStatus;
import com.my.vo.Student;

@Service
public class StudentService implements IStudentService {
	@Autowired
	private StudentDAOOracle sdao;
	
	@Override
	public List<Student> findStudentList(int currentPage, int cntPerPage, String word) throws FindException {
		List<Student> list = sdao.selectStudentList(currentPage, cntPerPage, word);
		return list;
	}
	
	@Override
	public int findCnt() throws FindException {
		int count = sdao.selectCnt();
		return count;
	}
	

	@Override
	public Student findById(int student_id) throws FindException {
		return sdao.selectById(student_id);
	}
	
	@Override
	public Student findByEmail(String studentEmail) throws FindException {
		return sdao.selectByEmail(studentEmail);
	}
	
	@Override
	public Student findByName(String studentName) throws FindException {
		return sdao.selectByName(studentName);
	}
	
	@Override
	public Student findByPwd(String inputPwd) throws FindException {
		return sdao.selectByPwd(inputPwd);
	}
	
	@Override
	public List<Penalty> findPenaltyAll() throws FindException {
		return sdao.selectPenaltyAll();
	}
	

	@Override
	public void add(Student s) throws AddException {
		sdao.insert(s);
	}

	@Override
	public void addPenaltyStatus(PenaltyStatus ps) throws AddException{
		sdao.insertPenaltyStatus(ps);
	}
	
	@Override
	public void modify(Student s) throws ModifyException {
		sdao.update(s);
	}
	
	@Override
	public void modify(Student s, String inputPwd) throws ModifyException {
		sdao.update(s, inputPwd);
	}
	
	@Override
	public void modifyStatus(int student_id) throws ModifyException {
		sdao.updateStatus(student_id);
	}

	@Override
	public void modifyAdminPwd(Student student, String certifyPwd) throws ModifyException {
		sdao.updateAdminPwd(student, certifyPwd);
		
	}

	@Override
	public Student login(String studentEmail, String studentPwd) throws FindException {
		try {
			Student s = sdao.selectByEmail(studentEmail);
			System.out.println(s);
			if(s.getStudentPwd().equals(studentPwd)) {
//				System.out.println("비밀번호일치");
//				System.out.println("로그인 성공");
				return s;
			}else {
				throw new FindException("로그인 실패");
			}
		}catch(FindException e) {
			throw new FindException("로그인 실패");
		}
	}





}
