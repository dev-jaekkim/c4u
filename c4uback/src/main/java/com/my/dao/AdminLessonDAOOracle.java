package com.my.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Lesson;
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
	public List<Lesson> selectLessonList(int currentPage, int cnt_per_page, String word) throws FindException {
		SqlSession session = null;
	try {	
		session = sqlSessionFactory.openSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("word", word);
		map.put("currentPage", currentPage);
		map.put("cnt_per_page", cnt_per_page);
		List<Lesson> list = session.selectList("mybatis.AdminLessonMapper.adminSelectByPage", map);
		if(list.size() == 0) {
			throw new FindException("심사내역이 없습니다.");
		}
		return list;
	}catch (Exception e) {
		throw new FindException(e.getMessage());
	}finally {
		if(session != null) {
			session.close();
		}
	}
	}

	@Override
	public Lesson selectLessonDetail(int lessonId) throws FindException {
		// TODO Auto-generated method stub
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
			LessonDAO ldao = new LessonDAOOracle();
			Lesson lesson = ldao.selectById(lessonId);
			if(!(lesson.getLessonStatus() == 3)){
				throw new ModifyException ("심사 대기 중인 강좌가 아닙니다.");
			}else { 
				session.insert("mybatis.AdminLessonMapper.updateLesson", lessonId);
				session.commit();
			}
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
