package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Lesson;
import com.my.vo.LessonPenalty;
import com.my.vo.LessonPenaltyStatus;

public interface AdminLessonDAO {
	
	/**
	 * 심사 대기중인 강좌 검색 및 조회
	 * @param lessonStatus 강좌 상태 
	 * @return List<Lesson> 심사 대기중인 강좌 리스트
	 * @throws ModifyException 심사 대기중인 강좌가 없을 경우 예외 발생
	 */
	public List<Lesson> selectEvalHold(int lessonStatus) throws FindException; 

	/**
	 * 강좌 전체 조회 및 검색
	 * @param currentPage 현재페이지
	 * @param cntPerPage 페이지 당 목록 수
	 * @param word 검색어
	 * @return List<Lesson> 강좌 전체 목록
	 * @throws FindException 검색어에 해당하는 강좌나 전체 강좌가 하나도 없을 경우 예외 발생 
	 */
	public List<Lesson> selectLessonList(int currentPage, int cntPerPage, String word) throws FindException;
	
	/**
	 * 레슨 아이디에 해당하는 레슨 심사상세 조회 
	 * @param lessonId 레슨 아이디
	 * @return Lesson 레슨 아이디에 해당하는 심사 신청 상세 정보
	 * @throws FindException 레슨 아이디에 해당하는 강좌가 존재하지 않을 경우 예외 발생
	 */
	public Lesson selectLessonDetail(int lessonId) throws FindException;
	
	/**
	 * 레슨 패널티 조회 
	 * @return 강좌 거절 사유
	 * @throws FindException 심사 거절 사유가 존재하지 않을 경우 예외 발생 
	 */
	public List<LessonPenalty> selectLessonPenaltyAll () throws FindException;
	
	/**
	 * 재심사시 참고용 심사 거절 내역 조회 
	 * @return 심사거절 내역 
	 * @throws FindException 
	 */
	public List<LessonPenalty> selectLessonPs(int lessonId) throws FindException;
	
	/** 
	 * 강좌 개설 거절 (심사 거절)
	 * @param lessonId 레슨 아이디
	 * @throws AddException 심사 거절 실패시 예외 발생
	 */
	public void insertPenaltyStatus(LessonPenaltyStatus lessonps) throws AddException;

	/**
	 * 강좌 개설 승인
	 * @param lessonId 심사 신청한 레슨 아이디
	 * @throws ModifyException 승인 실패시 예외 발생
	 */
	public void updateLesson (int lessonId) throws ModifyException;
	
	/**
	 * 강좌 삭제
	 * @param lessonId 레슨 아이디
	 * @throws RemoveException 강좌 삭제 실패시 예외 발생
	 */
	public void deleteLesson (int lessonId) throws RemoveException;
	
	/**
	 * 페이징 처리
	 * @return
	 * @throws FindException
	 */
	public int selectCnt() throws FindException;


}
