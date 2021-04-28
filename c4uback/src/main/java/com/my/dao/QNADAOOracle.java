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
import com.my.vo.QNA;
import com.my.vo.Student;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class QNADAOOracle implements QNADAO {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public List<QNA> selectPerPage(int currentPage, int cnt_per_page) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("currentPage", currentPage);
			map.put("cnt_per_page", cnt_per_page);
			List<QNA> list = session.selectList("mybatis.QNAMapper.selectPerPage", map);
			if(list.size() == 0) {
				throw new FindException("1:1 문의가 없습니다.");
			}
			log.debug(list);
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
	public int selectCnt() throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.selectOne("mybatis.QNAMapper.selectCnt");
			if(cnt == 0) {
				throw new FindException("1:1 문의 글이 없습니다");
			}
			log.debug(cnt);
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
	public List<QNA> selectByNameOrTitleOrContentPerPage(String word, int currentPage, int cnt_per_page) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("word", word);
			map.put("currentPage", currentPage);
			map.put("cnt_per_page", cnt_per_page);
			List<QNA> list = session.selectList("mybatis.QNAMapper.selectByNameOrTitleOrContentPerPage", map);
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
	public List<QNA> selectByStudentIdPerPage(int student_id, int currentPage, int cnt_per_page) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap();
			map.put("student_id", student_id);
			map.put("currentPage", currentPage);
			map.put("cnt_per_page", cnt_per_page);
			List<QNA> list = session.selectList("mybatis.QNAMapper.selectByStudentIdPerPage", map);
			if(list.size() == 0) {
				throw new FindException("작성한 1:1 문의가 없습니다.");
			}
			return list;
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!= null) {
				session.close();
			}
		}
	}

//	@Override 설계변경으로 사용하지 않음
//	public List<QNA> selectByNamePerPage(String student_name, int currentPage, int cnt_per_page) throws FindException {
//		SqlSession session = null;
//		try {
//			session = sqlSessionFactory.openSession();
//			Map<String, Object> map = new HashMap<>();
//			map.put("student_name", student_name);
//			map.put("currentPage", currentPage);
//			map.put("cnt_per_page", cnt_per_page);
//			List<QNA> list = session.selectList("mybatis.QNAMapper.selectByNamePerPage", map);
//			if(list.size() == 0) {
//				throw new FindException("검색 결과가 없습니다.");
//			}
//			log.debug(list);
//			return list;
//		}catch (Exception e) {
//			throw new FindException(e.getMessage());
//		}finally {
//			if(session != null) {
//				session.close();
//			}
//		}
//	}

	@Override
	public QNA selectById(int qna_id) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			QNA qna = session.selectOne("mybatis.QNAMapper.selectByIdAdmin", qna_id);
			if(qna == null) {
				throw new FindException("게시글이 존재하지 않습니다.");
			}
			log.debug(qna);
			return qna;
		}catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public QNA selectById(int qna_id, Student student) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("qna_id", qna_id);
			map.put("student", student);
			QNA qna = session.selectOne("mybatis.QNAMapper.selectByIdUser",map);
			if(qna == null) {
				throw new FindException("게시글 접근 권한이 없습니다.");
			}
			log.debug(qna);
			return qna;
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	

	@Override
	public void insert(QNA qna) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.insert("mybatis.QNAMapper.insert", qna);
			if(cnt == 0) {
				throw new AddException("1:1 문의 작성 실패!");
			}
		}catch(Exception e) {
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public QNA update(QNA qna) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.update("mybatis.QNAMapper.update", qna);
			if(cnt == 0) {
				throw new ModifyException("수정 실패!");
			}
			return qna;
		}catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public int delete(int qna_id) throws RemoveException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.delete("mybatis.QNAMapper.delete", qna_id);
			if(cnt == 0) {
				throw new RemoveException("삭제 실패!");
			}
			return qna_id;
		}catch(Exception e) {
			throw new RemoveException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
}
