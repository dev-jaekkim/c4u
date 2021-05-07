package com.my.dao;

import java.util.Calendar;
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
import com.my.vo.Lesson;

//수정
@Repository
public class LessonDAOOracle implements LessonDAO {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Lesson> selectAll() throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<Lesson> list = session.selectList("mybatis.LessonMapper.selectAll");
			if (list.size() == 0) {
				throw new FindException("강좌가 존재하지 않습니다.");
			}
			session.commit();
			Calendar enddate = Calendar.getInstance();
			Calendar sysdate = Calendar.getInstance();
			for(Lesson l : list) {
				enddate.setTime(l.getLessonEnd());
				long diffDays = (enddate.getTimeInMillis() - sysdate.getTimeInMillis()) / 1000 / (24*60*60);
				if(diffDays < 0) {
					diffDays = 0;
				}
				l.setDiffDays((int)diffDays);
			}
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public Lesson selectById(int lessonId) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Lesson lesson = session.selectOne("mybatis.LessonMapper.selectById", lessonId);
			if (lesson == null) {
				throw new FindException("해당 강좌가 존재하지 않습니다.");
			}
			session.commit();
//			Calendar enddate = Calendar.getInstance();
//			Calendar sysdate = Calendar.getInstance();
//			enddate.setTime(lesson.getLessonEnd());
//			long diffDays = (enddate.getTimeInMillis() - sysdate.getTimeInMillis()) / 1000 / (24*60*60);
//			if(diffDays < 0) {
//				diffDays = 0;
//			}
//			lesson.setDiffDays((int)diffDays);
			return lesson;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public List<Lesson> selectByUnion(String union) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<Lesson> list = session.selectList("mybatis.LessonMapper.selectByUnion", union);
			session.commit();
			Calendar enddate = Calendar.getInstance();
			Calendar sysdate = Calendar.getInstance();
			for(Lesson l : list) {
				enddate.setTime(l.getLessonEnd());
				long diffDays = (enddate.getTimeInMillis() - sysdate.getTimeInMillis()) / 1000 / (24*60*60);
				if(diffDays < 0) {
					diffDays = 0;
				}
				l.setDiffDays((int)diffDays);
			}
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public int selectCnt() throws FindException {
		SqlSession session =  null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.selectOne("mybatis.LessonMapper.selectCnt");
			if(cnt == 0) {
				throw new FindException("강좌가 없습니다.");
			}
			return cnt;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public int selectCnt(String word) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("word", word);
			int cnt = session.selectOne("mybatis.LessonMapper.selectByWordCnt", map);
			return cnt;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Lesson> selectPerPage(int currentPage, int cnt_per_page) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("currentPage", currentPage);
			map.put("cnt_per_page", cnt_per_page);
			List<Lesson> list = session.selectList("mybatis.LessonMapper.selectPerPage", map);
			if(list.size() == 0) {
				throw new FindException("강좌가 없습니다.");
			}
			session.commit();
			Calendar enddate = Calendar.getInstance();
			Calendar sysdate = Calendar.getInstance();
			for(Lesson l : list) {
				enddate.setTime(l.getLessonEnd());
				long diffDays = (enddate.getTimeInMillis() - sysdate.getTimeInMillis()) / 1000 / (24*60*60);
				if(diffDays < 0) {
					diffDays = 0;
				}
				l.setDiffDays((int)diffDays);
			}
			return list;
		}catch (Exception e){
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public List<Lesson> selectByUnionPerPage(String union, int currentPage, int cnt_per_page) throws FindException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("union", union);
			map.put("currentPage", currentPage);
			map.put("cnt_per_page", cnt_per_page);
			List<Lesson> list = session.selectList("mybatis.LessonMapper.selectByUnionPerPage", map);
			if(list.size() == 0) {
				throw new FindException("검색된 강좌가 없습니다.");
			}
			session.commit();
			Calendar enddate = Calendar.getInstance();
			Calendar sysdate = Calendar.getInstance();
			for(Lesson l : list) {
				enddate.setTime(l.getLessonEnd());
				long diffDays = (enddate.getTimeInMillis() - sysdate.getTimeInMillis()) / 1000 / (24*60*60);
				if(diffDays < 0) {
					diffDays = 0;
				}
				l.setDiffDays((int)diffDays);
			}
			return list;
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	};

	@Override
	public void insert(Lesson lesson) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("mybatis.LessonMapper.insert", lesson);
			session.commit();
		}catch (Exception e){
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public void update(Lesson lesson) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int rowCnt = session.update("mybatis.LessonMapper.update", lesson);
			if (rowCnt == 0) {
				throw new ModifyException("해당 강좌가 존재하지 않습니다.");
			}
			session.commit();
		}catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public int selectByTeacherIdCnt(int studentId) throws FindException {
		return 0;
	}

	@Override
	public List<Lesson> selectByLessonOpen(int studentId) throws FindException {
		SqlSession session = null;
	try {	
		session = sqlSessionFactory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("student_id", studentId);
		//map.put("lesson_id", lessonId);
		List<Lesson> list = session.selectList("mybatis.LessonMapper.selectByLessonOpen", map);
		if(list.size() == 0) {
			throw new FindException("개설한 강좌가 없습니다.");
		}
		return list;
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public List<Lesson> selectBySearch(String word) throws FindException {
		SqlSession session = null;
	try {	
		session = sqlSessionFactory.openSession();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("word", word);
		List<Lesson> list = session.selectList("mybatis.LessonMapper.selectByLessonSearch", map);
		if(list.size() == 0 ) {
			throw new FindException("검색결과가 없습니다.");
		}
		return list;
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public List<Lesson> selectByLessonStatus01234(int studentId, List<Integer> lessonStatus) throws FindException {
		SqlSession session = null;
		try {	
			session = sqlSessionFactory.openSession();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("student_id", studentId);
			map.put("lesson_status", lessonStatus);
			List<Lesson> list = session.selectList("mybatis.LessonMapper.selectByLessonStatus01234", map);
			if(list.size() == 0) {
				throw new FindException("개설한 강좌가 없습니다.");
			}
			return list;
			}catch (Exception e) {
				throw new FindException(e.getMessage());
			}finally {
				if(session != null) session.close();
			}
	}

	@Override
	public int selectAllCount() throws FindException {
		return 0;
	}

	
}
