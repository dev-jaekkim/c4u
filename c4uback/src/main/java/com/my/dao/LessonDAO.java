package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.vo.Lesson;

public interface LessonDAO {
	
	/**<pre>-강좌 전체 조회-
	 * 저장소의 모든 강좌를 반환한다</pre>
	 * @return List<Lesson> 저장소의 모든 강좌
	 * @throws FindException 강좌가 하나도 없으면 예외가 발생한다
	 */
	public List<Lesson> selectAll() throws FindException;
	
	/**<pre>-특정 강좌 조회-
	 * 저장소의 강좌아이디에 해당하는 강좌를 반환한다</pre>
	 * @param lessonId 강좌 아이디
	 * @return Lesson 강좌객체
	 * @throws FindException 강좌아이디에 해당하는 강좌가 없으면 예외가 발생한다
	 */
	public Lesson selectById(int lessonId) throws FindException;
	
	/**<pre>-다양한 이름으로 조회-
	 * 카테고리, 쌤, 강좌이름에  맞는 강좌를 반환한다</pre>
	 * @param union 카테고리, 쌤, 강좌 이름
	 * @return List<Lesson> 강좌 객체들
	 * @throws FindException 카테고리, 쌤, 강좌 이름에 해당하는 강좌가 없으면 예외가 발생한다
	 */
	public List<Lesson> selectByUnion(String union) throws FindException;
	/**
	 * 강좌 개설 신청
	 * @param lesson 학생 아이디, 강좌명, 목표금액, 개강일,
					수강료, 한줄소개, 레슨 카테고리 아이디
	 * @throws AddException 
	 */
	public void insert(Lesson lesson) throws AddException;
	
	/**
	 * 강좌 개설 재심사 신청
	 * @param lesson 수정된 강좌 정보들
	 * @throws ModifyException 
	 */
	public void update(Lesson lesson) throws ModifyException;
	
	
	/**
	 * 선생님 회원이 개설한 강좌수 조회 
	 * @param 학생 아이디
	 * @return 개설한 강좌수
	 * @throws FindException 회원 아이디에 해당하는 강좌가 없을 때 예외 발생
	 */
	public int selectByTeacherIdCnt(int studentId) throws FindException;
	
	/**
	 * 
	 * @param lesson_id
	 * @param student_email
	 * @return
	 * @throws FindException
	 */
	public List<Lesson> selectById(int lesson_id, String student_email) throws FindException;
	
	public int selectAllCount() throws FindException;
	
	public List<Lesson> selectByPage(int currPage, int dataPerPage) throws FindException;
}