package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.sql.MyConnection;
import com.my.vo.Lesson;
import com.my.vo.Student;

import lombok.extern.log4j.Log4j;
//수정
@Repository
@Log4j
public class LessonDAOOracle implements LessonDAO {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired 
	private Student student;

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
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		String selectAllCountSQL = "SELECT COUNT (*) FROM lesson";
		try {
			pstmt = con.prepareStatement(selectAllCountSQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int selectAllCount = rs.getInt("COUNT(*)");
				return selectAllCount;
			}else {
				throw new FindException("강좌가 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(con,pstmt,rs);
		}
	}

	@Override
	public List<Lesson> selectPerPage(int currPage, int dataPerPage) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		String selectByPageSQL = "SELECT * FROM (SELECT LESSON_ID,LESSON_TEACHER_ID,LESSON_NAME,LESSON_TOTAL_AMOUNT,LESSON_TARGET_AMOUNT,LESSON_PARTICIPANT,LESSON_STATUS,LESSON_CREATE_DATE,LESSON_END_DATE,LESSON_START_DATE,LESSON_FEE,LESSON_DESCRIPTION,LESSON_CATEGORY_ID,LESSON_RECOMMEND_CNT, row_number() OVER (ORDER BY lesson_end_date) AS rnum FROM lesson WHERE lesson_status = 0) WHERE rnum BETWEEN fun_start_row(?, ?) AND fun_end_row(?, ?)";
		List<Lesson> currPageList = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(selectByPageSQL);
			pstmt.setInt(1, currPage);
			pstmt.setInt(2, dataPerPage);
			pstmt.setInt(3, currPage);
			pstmt.setInt(4, dataPerPage);
			rs = pstmt.executeQuery();
			Calendar enddate = Calendar.getInstance();
			Calendar sysdate = Calendar.getInstance();
			sysdate.setTime(new Date());
			while(rs.next()) {
				int lessonId = rs.getInt("lesson_id");
				String lessonName = rs.getString("lesson_name");
				int lessonTargetFee = rs.getInt("lesson_target_amount");
				int lessonTotalFee = rs.getInt("lesson_total_amount");;
				String lessonDescription = rs.getString("lesson_description");
				Date lessonEnd = rs.getDate("lesson_end_date");
				int categoryId = rs.getInt("lesson_category_id");
				enddate.setTime(lessonEnd);
				long diffDays = (enddate.getTimeInMillis() - sysdate.getTimeInMillis()) / 1000 / (24*60*60);
				if(diffDays < 0) {
					diffDays = 0;
				}
				Lesson l = new Lesson(lessonId, lessonName, lessonDescription, lessonTargetFee, lessonTotalFee, lessonTotalFee * 100/lessonTargetFee, (int)diffDays, categoryId);
				currPageList.add(l);
			}
			if(currPageList.size() == 0) {
				throw new FindException("강좌가 하나도 없습니다");
			}
			return currPageList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(con,pstmt,rs);
		}
	}
	
	@Override
	public List<Lesson> selectByUnionPerPage(String union, int currentPage, int cnt_per_page) throws FindException{
		return null;
	};

	@Override
	public void insert(Lesson lesson) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			log.info("--dao--" + lesson);
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

	@Override
	public int selectCnt(String word) throws FindException {
		// TODO Auto-generated method stub
		return 0;
	}
}
