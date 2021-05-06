package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.vo.Lesson;

public interface LessonDAO {
	
	/**(SPRING)<pre>-강좌 전체 조회-
	 * 저장소의 모든 강좌를 반환한다</pre>
	 * @return List<Lesson> 저장소의 모든 강좌
	 * @throws FindException 강좌가 하나도 없으면 예외가 발생한다
	 */
	List<Lesson> selectAll() throws FindException;
	
	/**(SPRING)<pre>-특정 강좌 조회-
	 * 저장소의 강좌아이디에 해당하는 강좌를 반환한다</pre>
	 * @param lessonId 강좌 아이디
	 * @return Lesson 강좌객체
	 * @throws FindException 강좌아이디에 해당하는 강좌가 없으면 예외가 발생한다
	 */
	Lesson selectById(int lessonId) throws FindException;
	
	/**(SPRING)<pre>-다양한 이름으로 조회-
	 * 카테고리, 쌤, 강좌이름에  맞는 강좌를 반환한다</pre>
	 * @param union 카테고리, 쌤, 강좌 이름
	 * @return List<Lesson> 강좌 객체들
	 * @throws FindException 카테고리, 쌤, 강좌 이름에 해당하는 강좌가 없으면 예외가 발생한다
	 */
	List<Lesson> selectByUnion(String union) throws FindException;
	
	/** 사용자 사용
	 * 메인페이지 페이징 처리를 위하여, 강좌 리스트 전체 조회
	 * @return int DB에 저장된 강좌 개수 (개설된 강좌만 조회)
	 * @throws FindException 개설된 강좌가 없으면, 예외가 발생한다.
	 */
	int selectCnt() throws FindException;
	
	/** 사용자 사용
	 * 메인 페이지 강좌 검색 시 페이징 처리를 위하여, 검색된 강좌 리스트 전체 조회
	 * @param word 검색 단어
	 * @return int DB에 검색된 word와 일치하는 검색 결과
	 * @throws FindException 검색 결과가 없으면, 예외가 발생한다. 
	 */
	int selectCnt(String word) throws FindException;
	
	/** 사용자 사용
	 * 메인페이지 현재 개설된 강좌를 보여준다.(페이지별로)
	 * @param currentPage 메인 페이지의 강좌 리스트 현재 페이지
	 * @param cnt_per_page 한 페이지 당 존재하는 게시물 수 
	 * @return List<Lesson> 강좌 리스트
	 * @throws FindException 개설된 강좌가 없으면, 예외가 발생한다. 
	 */
	List<Lesson> selectPerPage(int currentPage, int cnt_per_page) throws FindException;
	
	/** 사용자 사용
	 * 메인페이지 검색 결과를 페이지별로 보여준다. 
	 * @param union 검색 단어
	 * @param currentPage 검색된 강좌의 강좌 리스트 현재 페이지
	 * @param cnt_per_page 한 페이지 당 존재하는 게시 물 수
	 * @return List<Lesson> 강좌 리스트
	 * @throws FindException 검색된 강좌가 없으면, 예외가 발생한다.
	 */
	List<Lesson> selectByUnionPerPage(String union, int currentPage, int cnt_per_page) throws FindException;
		
	/**(SPRING)
	 * 강좌 개설 신청
	 * @param lesson 학생 아이디, 강좌명, 목표금액, 개강일,
					수강료, 한줄소개, 레슨 카테고리 아이디
	 * @throws AddException 
	 */
	void insert(Lesson lesson) throws AddException;
	
	/**(SPRING)
	 * 강좌 개설 재심사 신청
	 * @param lesson 수정된 강좌 정보들
	 * @throws ModifyException 
	 */
	void update(Lesson lesson) throws ModifyException;
	
	/**
	 * 선생님 회원이 개설한 강좌수 조회 
	 * @param 학생 아이디
	 * @return 개설한 강좌수
	 * @throws FindException 회원 아이디에 해당하는 강좌가 없을 때 예외 발생
	 */
	int selectByTeacherIdCnt(int studentId) throws FindException;
	
	/**
	 * (SPRING)
	 * @param lesson_id 선생님 회원이 개설한 강좌 아이디 
	 * @param student_email 선생님 회원 이메일
	 * @return
	 * @throws FindException
	 */
	//public List<Lesson> selectById(int lesson_id, String student_email) throws FindException;
	List<Lesson> selectByLessonOpen(int studentId) throws FindException;
	
	/**(SPRING) 사용자 사용 : 마이페이지
	 * 내가 개설한 강좌 검색
	 * @param word 검색어
	 * @return
	 * @throws FindException
	 */
	List<Lesson> selectBySearch(String word) throws FindException;
	
	/*(SPRING)
	 * @param studentId
	 * @param lesson_status
	 * @return
	 * @throws FindException 해당 아이디에 개설한 강좌가 없을때 예외 발생
	 * 
	 */
	List<Lesson> selectByLessonStatus01234 (int studentId, List<Integer> lesson_status) throws FindException;

	int selectAllCount() throws FindException;

	//List<Lesson> selectBySearch(int lessonId, int studentId, String union1, String union2) throws FindException;

}