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
import com.my.vo.Notice;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class NoticeDAOOracle implements NoticeDAO{
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public List<Notice> selectPerPage(int currentPage, int cnt_per_page) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("currentPage", currentPage);
			map.put("cnt_per_page", cnt_per_page);
			List<Notice> list = session.selectList("mybatis.NoticeMapper.selectPerPage", map);
			if(list.size()==0) {
				throw new FindException("게시글이 없습니다.");
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
	public int selectCnt() throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.selectOne("mybatis.NoticeMapper.selectCnt");
			if(cnt == 0) {
				throw new FindException("게시글이 없습니다.");
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
	public List<Notice> selectByTitlePerPage(String notice_title, int currentPage, int cnt_per_page)
			throws FindException {
		
		SqlSession session= null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("notice_title", notice_title);
			map.put("currentPage", currentPage);
			map.put("cnt_per_page", cnt_per_page);
			
			List<Notice> list = session.selectList("mybatis.NoticeMapper.selectByTitlePerPage", map);
			if(list.size()==0) {
				throw new FindException("검색결과가 없습니다.");
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
	public Notice selectById(int notice_id) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Notice notice = session.selectOne("mybatis.NoticeMapper.selectById", notice_id);
			if(notice == null) {
				throw new FindException("일치하는 게시물이 없습니다.");
			}
			return notice;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public Notice update(Notice notice) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.update("mybatis.NoticeMapper.update", notice);
			if(cnt == 0) {
				throw new ModifyException("수정된 게시글이 없습니다.");
			}
			return notice;			
		}catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public int delete(int notice_id) throws RemoveException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.delete("mybatis.NoticeMapper.delete",notice_id);
			if(cnt== 0) {
				throw new RemoveException("삭제할 게시물이 없습니다.");
			}
			return notice_id;
		}catch (Exception e) {
			throw new RemoveException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void insert(Notice notice) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int cnt = session.insert("mybatis.NoticeMapper.insert", notice);
			if(cnt == 0) {
				throw new AddException("공지사항 작성 실패!");
			}
		}catch(Exception e) {
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
	}
}