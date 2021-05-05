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
import com.my.exception.RemoveException;
import com.my.vo.LessonReview;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class LessonReviewDAOOracle implements LessonReviewDAO {
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Override
	public List<LessonReview> selectPerPage(int currentPage, int cnt_per_page) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("currentPage", currentPage);
			map.put("cnt_per_page", cnt_per_page);
			List<LessonReview> list = session.selectList("mybatis.LessonReviewMapper.adminSelectPerPage", map);
			if(list == null) {
				throw new FindException("게시글이 없습니다.");
			}
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<LessonReview> selectPerPage(int lessonId, int currentPage, int cnt_per_page) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("lessonId", lessonId);
			map.put("currentPage", currentPage);
			map.put("cnt_per_page", cnt_per_page);
			List<LessonReview> list = session.selectList("mybatis.LessonReviewMapper.userSelectPerPage", map);
			if(list.size() == 0) {
				throw new FindException("수강 후기가 없습니다.");
			}
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public int selectCnt() throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.selectOne("mybatis.LessonReviewMapper.adminSelectCnt");
			if(cnt == 0) {
				throw new FindException("수강 후기가 없습니다.");
			}
			return cnt;
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public int selectCnt(int lesson_id) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.selectOne("mybatis.LessonReviewMapper.userSelectCnt", lesson_id);
			if(cnt == 0) {
				throw new FindException("수강 후기가 없습니다.");
			}
			return cnt;
		}catch (Exception e) {
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
			int cnt = session.selectOne("mybatis.LessonReviewMapper.adminSelectCntByWord", map);
			if(cnt == 0) {
				throw new FindException("수강 후기가 없습니다.");
			}
			return cnt;
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<LessonReview> selectByLessonTitleORStudentNamePerPage(String word, int currentPage, int cnt_per_page)
			throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("word", word);
			map.put("currentPage", currentPage);
			map.put("cnt_per_page", cnt_per_page);
			List<LessonReview> list = session.selectList("mybatis.LessonReviewMapper.selectByLessonTitleORStudentNamePerPage", map);
			if(list.size() == 0) {
				throw new FindException("검색 결과가 없습니다.");
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
	public void insert(LessonReview lessonReview) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.insert("mybatis.LessonReviewMapper.insert",lessonReview);
			if(cnt == 0) {
				throw new AddException("삽입 실패!");
			}
		}catch (Exception e) {
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public int delete(int review_id) throws RemoveException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.delete("mybatis.LessonReviewMapper.delete", review_id);
			if(cnt == 0) {
				throw new RemoveException("삭제 실패!");
			}
			return review_id;
		}catch(Exception e) {
			throw new RemoveException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

}