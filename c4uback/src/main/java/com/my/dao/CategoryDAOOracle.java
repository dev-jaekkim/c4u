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
	//			String selectLessonByCategorySQL = "SELECT * FROM lesson WHERE lesson_status = 0 AND (lesson_category_id = (SELECT category_id FROM category WHERE category_id = ?) OR lesson_category_id in (SELECT category_id FROM category WHERE category_parent_id = ?)) ORDER BY lesson_end_date";
	//			List<Lesson> all = new ArrayList<>();
	//			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	//			Calendar enddate = Calendar.getInstance();
	//			Calendar sysdate = Calendar.getInstance();
	//			sysdate.setTime(new Date());
	//			try {
	//				pstmt = con.prepareStatement(selectLessonByCategorySQL);
	//				pstmt.setInt(1, categoryId);
	//				pstmt.setInt(2, categoryId);
	//				rs = pstmt.executeQuery();
	//				while(rs.next()) {
	//					int lessonId = rs.getInt("lesson_id");
	//					String lessonName = rs.getString("lesson_name");
	//					int lessonTargetFee = rs.getInt("lesson_target_amount");
	//					int lessonTotalFee = rs.getInt("lesson_total_amount");;
	//					String lessonDescription = rs.getString("lesson_description");
	//					Date lessonEnd = rs.getDate("lesson_end_date");
	//					int lessonCategoryId = rs.getInt("lesson_category_id");
	//					enddate.setTime(lessonEnd);
	//					long diffDays = (enddate.getTimeInMillis() - sysdate.getTimeInMillis()) / 1000 / (24*60*60);
	//					if(diffDays < 0) {
	//						diffDays = 0;
	//					}
	//					Lesson l = new Lesson();
	//					l.setLessonId(lessonId);
	//					l.setLessonName(lessonName);
	//					l.setLessonDescription(lessonDescription);
	//					l.setLessonTotalFee(lessonTotalFee);
	//					l.setTargetPercent(lessonTotalFee*100/lessonTargetFee);
	//					l.setDiffDays((int)diffDays); 
	//					l.setLessonCategory(lessonCategoryId);
	//					all.add(l);
	//				}
	//				return all;
	//			} catch (SQLException e) {
	//				e.printStackTrace();
	//			}
	//			return null;
	//		}


	//	public static void main(String[]args) {
	//		CategoryDAO dao = new CategoryDAOOracle();
	//		try {
	//			List<Lesson> list = dao.selectById(101);
	//			for(Lesson l : list) {
	//				System.out.println(l.getLessonName() +":"+ l.getLessonDescription() +":"+ l.getLessonTotalFee() +":"+ l.getTargetPercent() +":"+ l.getDiffDays());
	//			}
	//		} catch (FindException e) {
	//			e.printStackTrace();
	//		}
	//		try {
	//			List<Category> list = dao.selectAll();
	//			for(Category c : list) {
	//				System.out.println(c.getCategoryId() +":"+ c.getCategoryName() +":"+ c.getParentCategoryId());
	//			}
	//		} catch (FindException e) {
	//			e.printStackTrace();
	//		}
	//	}
}
