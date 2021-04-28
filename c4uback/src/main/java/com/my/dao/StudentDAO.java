package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Student;

public interface StudentDAO {

	   /**아이디로 학생 조회
	    * 저장소의 아이디에 해당학생을 반환한다
	    * @param id 아이디
	    * @return 학생객체
	    * @throws FindException 아이디에 해당학생이 없으면 발생한다
	    */
	   public abstract Student selectById(int id) throws FindException;
	   
	   /**
	    * 로그인 - 비밀번호 찾기에서 학생이 입력한 이메일로 이메일을 전송위한 메소드, 마이페이지- 내정보를 찾을 떄, 학생이 입력한 이메일을 이용하여 학생 찾기
	    * @param email 학생 아이디
	    * @return 학생 객체
	    * @throws FindException 아이디에 해당하는 학생이 없으면 예외 발생
	    */
	   public abstract Student selectByEmail(String studentEmail) throws FindException;

	   
	   /**
	    * 닉네임 중복 체크시, DB에서 닉네임 중복 여부 체크
	    * @param name 학생 닉네임
	    * @return 학생 객체
	    * @throws FindException 입력한 닉네임과 일치하는 닉네임이 없으면 예외 발생
	    */
	   public abstract Student selectByName(String studentName) throws FindException;
	   /**
	    * 마이페이지 - 내 정보에서 현재 비밀번호 확인 
	    * @param studentPwd
	    * @param studentId
	    * @return
	    * @throws FindException
	    */
	   public abstract Student selectByPwd(String inputPwd) throws FindException;
	   /**회원가입시 학생 추가
	    * 저장소에 학생정보를 저장한다
	    * @param s 학생 객체
	    * @throws AddException 아이디가 이미 존재하는 경우,
	    *                      저장소가 꽉찬경우 발생한다                 
	    */
	   public abstract void insert(Student s) throws AddException;
	   /**
	    * 학생 회원 경고 주기
	    * @param studentId 학생 아이디
	    * @throws AddException
	    */
	   public void insertPenalty(int studentId) throws AddException;
	   
	   /**회원 정보 수정
	    * 학생정보를 수정한다. 단, 아이디는 수정할 수 없다
	    * @param s  변경될 내용이 담겨있는 학생객체
	    * @return   변경된 학생객체
	    * @throws  ModifyException 수정실패시 예외발생한다
	    */
	   public abstract Student update(Student s) throws ModifyException;
	   
	   /**
	    * 회원정보 수정
	    * 학생 정보를 수정한다. 단, 아이디는 수정할 수 없다.
	    * @param s 변경될 내용이 담겨있는 학생 객체
	    * @param inputPwd 사용자가 입력한 기존 비밀번호
	    * @return 변경된 학생 객체
	    * @throws ModifyException
	    */
	   public abstract Student update(Student s, String inputPwd) throws ModifyException;
	   
	   
//	   public List<Student> selectAll (int)
	   
	   
	   /**
	    * 회원 전체 조회 및 검색
	    * @param currentPage 현재 페이지
	    * @param cntPerPage 페이지 당 목록수
	    * @param word 검색어
	    * @return 회원 전체 리스트 또는 검색어에 해당하는 회원 
	    * @throws FindException 검색어에 해당하는 회원이나 회원이 한 명도 없을 경우 예외 발생
	    */
	   public List<Student> selectStudentList(int currentPage, int cntPerPage, String word) throws FindException;
	   
	   /**
	    * 관리자의 회원 탈퇴처리
	    * @param studentId 학생 아이디
	    * @throws RemoveException 탈퇴처리 실패시 예외 발생
	    */
	   public void updateStatus(int studentId) throws RemoveException;
	   
	   /**
	    * 관리자 비밀번호 변경
	    * @param student
	    * @param certifyPwd 기존 비밀번호
	    * @throws ModifyException 비밀번호 수정실패 시 예외 발생
	    */
	   public void updateAdminPwd (Student student, String certifyPwd) throws ModifyException;
	   
	   
	   public int selectCnt() throws FindException;
	   
}