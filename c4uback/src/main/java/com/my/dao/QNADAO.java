package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.QNA;
import com.my.vo.Student;

public interface QNADAO {
		
	/**사용자, 관리자 모두 사용
	 * 1:1 문의 전체 목록을 페이지 별로 조회
	 * @param currentPage 1:1 문의 현재 페이지
	 * @param cnt_per_page 한 페이지 당 존재하는 게시물 수
	 * @return List<QNA> 1:1 문의 리스트
	 * @throws FindException 1:1 문의가 없으면, 예외가 발생한다. 
	 */
	List<QNA> selectPerPage(int currentPage, int cnt_per_page) throws FindException;
	
	/** 사용자, 관리자 모두 사용
	 * 1:1문의 페이징 처리를 위하여, 게시글 전체 조회
	 * @return DB에 저장된 1:1 게시글 개수
	 * @throws FindException 1:1 문의 게시글이 없다면, 예외가 발생한다.
	 */
	int selectCnt() throws FindException;
	
	/** 관리자  사용
	 * 1:1문의 검색 페이징 처리를 위하여, 게시글 전체 조회
	 * @param word 검색 닉네임 또는 제목 또는 문의 내용
	 * @return DB에 저장된 1:1 게시글 개수
	 * @throws FindException 1:1 문의 게시글이 없다면, 예외가 발생한다.
	 */
	int selectCnt(String word) throws FindException;
	
	/**사용자 사용 (설계변경으로 사용하지 않음)
	 * 1:1 문의 게시판 중 닉네임이 일치하는 글 조회
	 * @param student_name 검색 닉네임
	 * @param currentPage 현재 페이지
	 * @param cnt_per_page 한 페이지당 게시 글 수
	 * @return List<QNA> 검색된 1:1 문의 목록
	 * @throws FindException 닉네임이 일치하는 1:1 문의가 없을 경우, 예외가 발생한다. 
	 */
	//List<QNA> selectByNamePerPage(String student_name, int currentPage, int cnt_per_page) throws FindException;
	
	/** 사용자 사용
	 * 사용자가 본인이 작성한 1:1문의 전체 조회
	 * @param student_id 사용자 id
	 * @param currentPage 현재 페이지
	 * @param cnt_per_page 한 페이지당 게시글 수
	 * @return List<QNA> 검색된 1:1 문의 목록
	 * @throws FindException 사용자 id로 조회된 1:1문의가 없을 경우, 예외가 발생한다.
	 */
	List<QNA> selectByStudentIdPerPage(int student_id, int currentPage, int cnt_per_page) throws FindException;
	
	/**관리자 사용
	 * 1:1 문의 게시판 중 닉네임, 문의 내용, 제목이 일치하는 글 조회
	 * @param word 검색 닉네임 또는 제목 또는 문의 내용
	 * @param currentPage 현재 페이지
	 * @param cnt_per_page 한 페이지당 게시 글 수
	 * @return List<QNA> 검색된 1:1 문의 목록
	 * @throws FindException 닉네임이 일치하는 1:1 문의가 없을 경우, 예외가 발생한다. 
	 */
	List<QNA> selectByNameOrTitleOrContentPerPage(String word, int currentPage, int cnt_per_page) throws FindException;
	
	/** 관리자 사용
	 * 1:1문의 상세 조회 (관리자는 모두 조회 가능)
	 * @param qna_id 1:1문의 게시글
	 * @return QNA QNA 객체
	 * @throws FindException 1:1 문의 번호에 해당하는 1:1 게시글이 없으면, 예외가 발생한다. 
	 */
	QNA selectById(int qna_id) throws FindException;
	
	/** 사용자 사용
	 * 1:1문의 상세 조회 (작성자만 조회 가능)
	 * @param qna_id 1:1문의 게시글 번호
	 * @param student 게시글을 클릭한 학생 객체
	 * @return QNA QNA 객체
	 * @throws FindException 1) 1:1문의 번호에 해당하는 1:1 게시글이 없으면, 예외가 발생한다. 
	 * 						 2) 게시글을 클릭한 학생과 1:1 게시글을 작성한 작성자가 같지 않으면, 예외가 발생한다. 
	 */
	QNA selectById(int qna_id, Student student) throws FindException;
	
	/**사용자 사용
	 * 1:1 문의 1개 추가
	 * @param qna 작성한 1:1 문의 객체
	 * @throws AddException 작성한 1:1 문의 객체가 없을 경우, 예외가 발생한다.
	 */
	void insert(QNA qna) throws AddException;
	
	/** 관리자 사용
	 * 1:1 문의 답변 달기 (기존 1:1 문의 수정)
	 * @param qna 답변이 달린 1:1 문의 객체
	 * @return QNA 답변이 달린 1:1 문의 객체
	 * @throws ModifyException 수정할 1:1 문의 객체가 없을 경우, 예외가 발생한다.
	 */
	QNA update(QNA qna) throws ModifyException;
	
	/** 관리자 사용
	 * 1:1 문의 객체 1개 삭제
	 * @param qna_id 삭제하고자 하는 1:1 문의 qna_id
	 * @return int 삭제한 1:1 문의 번호
	 * @throws RemoveException 삭제할 1:1문의 객체가 없을 경우, 예외가 발생한다.
	 */
	int delete(int qna_id) throws RemoveException;
}