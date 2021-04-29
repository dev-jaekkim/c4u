package com.my.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.vo.Penalty;
import com.my.vo.PenaltyStatus;
import com.my.vo.Student;

public interface IStudentService {
	
	List<Student> findStudentList(int currentPage, int cnt_per_page, String word) throws FindException;
	
	Student findById(int studentId) throws FindException ;
	
	int findCnt () throws FindException;

	Student findByEmail(String studentEmail) throws FindException;

	Student findByName(String studentName) throws FindException;

	Student findByPwd(String inputPwd) throws FindException;

	List<Penalty> findPenaltyAll() throws FindException;

	void add(Student s) throws AddException;

	void addPenaltyStatus(PenaltyStatus ps) throws AddException;

	void modify(Student s) throws ModifyException;

	void modify(Student s, String inputPwd) throws ModifyException;

	void modifyStatus (int student_id) throws ModifyException;

	void modifyAdminPwd(Student student, String certify_student_pwd) throws ModifyException;

	Student login(String studentEmail, String studentPwd) throws FindException;



}
