package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.RemoveException;
import com.my.vo.LessonReview;

public interface LessonReviewDAO {

	//2021-04-29 김재경: 관리자용 메서드 추가
	/** 관리자 사용
	 * 수강 후기 관리 게시판 전체 목록 (모든 강좌) 페이지 별로 조회
	 * @param currentPage 수강 후기 현재 페이지
	 * @param cnt_per_page 한 페이지 당 존재하는 게시물 수
	 * @return List<LessonReview> 수강후기 리스트
	 * @throws FindException 수강후기가 없으면, 예외가 발생한다. 
	 */
	List<LessonReview> selectPerPage(int currentPage, int cnt_per_page) throws FindException;
	
	/**사용자 사용
	 * 1 강좌의 수강 후기 페이지 별로 조회
	 * @param currentPage 수강 후기 현재 페이지
	 * @param cnt_per_page 한 페이지 당 존재하는 게시물 수
	 * @param lessonId 조회하고자 하는 강좌 ID
	 * @return List<LessonReview> 수강후기 리스트
	 * @throws FindException 수강후기가 없으면, 예외가 발생한다. 
	 */
	List<LessonReview> selectPerPage(int lessonId, int currentPage, int cnt_per_page) throws FindException;
	
	/** 관리자 사용
	 * 수강후기 관리 게시판 페이징 처리를 위하여, 게시글 전체 조회
	 * @return DB에 저장된 수강후기 게시글 개수
	 * @throws FindException 수강후기가 없으면, 예외가 발생한다.
	 */
	int selectCnt() throws FindException;
	
	/**사용자 사용
	 * 수강후기 관리 게시판 페이징 처리를 위하여, 게시글 전체 조회
	 * @param lessonId 조회하고자 하는 수업
	 * @return DB에 저장된 수강후기 게시글 개수
	 * @throws FindException 수강후기가 없으면, 예외가 발생한다.
	 */
	int selectCnt(int lessonId) throws FindException;
	
	/** 관리자 사용
	 * 수강 후기 관리 게시판 중 강좌 이름, 학생 이름 일치하는 글 조회
	 * @param word 검색 강좌 이름 또는 학생 이름 
	 * @param currentPage 현재 페이지
	 * @param cnt_per_page 한 페이지 당 게시글 수
	 * @return List<LessonReview> 검색된 수강 후기 게시글 목록
	 * @throws FindException 검색된 내용이 없을 경우, 예외가 발생한다.
	 */
	List<LessonReview> selectByLessonTitleORStudentNamePerPage(String word, 
															   int currentPage, 
															   int cnt_per_page) throws FindException;
	
	/** 사용자 사용
	 * 수강 후기 1개 추가 (수강한 수업만) 
	 * @param review 작성한 수강 후기 객체 
	 * @throws AddException 수강 후기를 이미 남겼을 경우, 예외가 발생한다.
	 */
	void insert(LessonReview review) throws AddException;
	
	/** 관리자 사용
	 * 수강 후기 관리 게시판 객체 1개 삭제
	 * @param reviewId 삭제하고자 하는 수강 후기 review_id
	 * @return int 삭제한 수강 후기 번호
	 * @throws RemoveException 삭제할 수강 후기 객체가 없을 경우, 예외가 발생한다.
	 */
	int delete(int reviewId) throws RemoveException;
}