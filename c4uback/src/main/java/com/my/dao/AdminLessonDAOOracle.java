package com.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Lesson;
import com.my.vo.LessonPenalty;
import com.my.vo.LessonPenaltyStatus;
@Repository
public class AdminLessonDAOOracle implements AdminLessonDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Lesson> selectEvalHold(int lessonStatus) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<Lesson> list = session.selectList("mybatis.AdminLessonMapper.selectEvalHold", lessonStatus);
			if (list.size() == 0) {
				throw new FindException("강좌가 존재하지 않습니다.");
			}
			session.commit();
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public List<Lesson> selectLessonList(int currentPage, int cntPerPage, String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lesson selectLessonDetail(int lessonId) throws FindException {
//		SqlSession session = null;
//		try { 
//			session = sqlSessionFactory.openSession();
//			Lesson lesson = session.selectOne("mybatis.AdminLessonMapper.selectLessonDetail", lessonId);
//			if (lesson == null) {
//				throw new FindException("해당 강좌가 존재하지 않습니다.");
//			}
//			session.commit();
//			return lesson;
//		}catch(Exception e) {
//			throw new FindException(e.getMessage());
//		}finally {
//			if(session != null) session.close();
//		}
		return null;
	}
	
	@Override
	public List<LessonPenalty> selectLessonPenaltyAll() throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<LessonPenalty> list = session.selectList("mybatis.AdminLessonMapper.selectLessonPenaltyAll");
			if (list.size() == 0) {
				throw new FindException("심사 거절 사유가 존재하지 않습니다.");
			}
			session.commit();
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}
	
	@Override
	public List<LessonPenalty> selectLessonPs(int lessonId) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("mybatis.AdminLessonMapper.selectLessonPs", lessonId);
			session.commit();
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
		return null;
		
	}


	@Override
	public void insertPenaltyStatus(LessonPenaltyStatus lessonPs) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("mybatis.AdminLessonMapper.insertPenaltyStatus", lessonPs);
			session.commit();
		}catch(Exception e) {
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public void updateLesson(int lessonId) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("mybatis.AdminLessonMapper.updateLesson", lessonId);
			session.commit();
		}catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public void deleteLesson(int lessonId) throws RemoveException {
		// TODO Auto-generated method stub

	}

	@Override
	public int selectCnt() throws FindException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

}
