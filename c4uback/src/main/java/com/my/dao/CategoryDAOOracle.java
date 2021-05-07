package com.my.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.FindException;
import com.my.vo.Category;
import com.my.vo.Lesson;

import lombok.extern.log4j.Log4j;
@Repository
@Log4j
public class CategoryDAOOracle implements CategoryDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Category> selectAll() throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<Category> all = session.selectList("mybatis.CategoryMapper.selectAll");
			if(all.size() == 0) {
				throw new FindException("카테고리가 없습니다.");
			}
			session.commit();
			return all;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) session.close();
		}
	}

	@Override
	public List<Lesson> selectById(int categoryId) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<Lesson> all = session.selectList("mybatis.CategoryMapper.selectById", categoryId);
			if(all.size() == 0) {
				throw new FindException("아이디에 해당하는 카테고리가 없습니다.");
			}
			session.commit();
			Calendar enddate = Calendar.getInstance();
			Calendar sysdate = Calendar.getInstance();
			for(Lesson l : all) {
				enddate.setTime(l.getLessonEnd());
				long diffDays = (enddate.getTimeInMillis() - sysdate.getTimeInMillis()) / 1000 / (24*60*60);
				if(diffDays < 0) {
					diffDays = 0;
				}
				l.setDiffDays((int)diffDays);
			}
			return all;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) session.close();
		}
	}
}