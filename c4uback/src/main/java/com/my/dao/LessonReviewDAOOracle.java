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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCnt() throws FindException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCnt(int lessonId) throws FindException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LessonReview> selectByLessonTitleORStudentNamePerPage(String word, int currentPage, int cnt_per_page)
			throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(LessonReview review) throws AddException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delete(int reviewId) throws RemoveException {
		// TODO Auto-generated method stub
		return 0;
	}


//   @Override
//   public List<LessonReview> selectAll() throws FindException {
//      Connection con = null;
//      PreparedStatement pstmt = null;
//      ResultSet rs = null;
//      try {
//         con = MyConnection.getConnection();
//      } catch (Exception e) {
//         e.printStackTrace();
//         throw new FindException(e.getMessage());
//      }
//      String selectAllSQL = "select * from review order by review_id desc";
//      List<LessonReview> all = new ArrayList<>();
//      
//      try {
//         pstmt = con.prepareStatement(selectAllSQL);
//         rs = pstmt.executeQuery();
//         
//         while(rs.next()) {
//            String reviewContent = rs.getString("review_content");
//            Date reviewDate = rs.getDate("review_date");
//            int recommend = rs.getInt("review_recommendation");
//            LessonReview rv = new LessonReview(reviewContent, reviewDate, recommend);
//            all.add(rv);
//         }
//         if(all.size()==0) {
//            throw new FindException("리뷰가 하나도 없습니다");
//         } return all;
//         
//      } catch (SQLException e) {
//         e.printStackTrace();
//         throw new FindException(e.getMessage());
//      } finally {
//         MyConnection.close(con, pstmt, rs);
//      }
//   }
//
//   @Override
//   public void insert(LessonReview review) throws AddException {
//      Connection con = null;
//      PreparedStatement pstmt = null;
//      try {
//         con = MyConnection.getConnection();
//      } catch (Exception e) {
//         e.printStackTrace();
//         throw new AddException("리뷰 추가 실패: 이유=" + e.getMessage());
//      }
//      LPSDAO lpsd = new LPSDAOOracle();
//   
// 
//      String insertRvSQL = "INSERT INTO review(review_id, review_date, review_content,"
//            + "review_recommendation, review_lps_id) VALUES(seq_review_id.NEXTVAL, ?, ?, ?, ?)";
//      
//      try {
//    	  Date reviewdate = review.getReviewDate();
//          java.sql.Date sqldate = new java.sql.Date(reviewdate.getTime());
//         pstmt = con.prepareStatement(insertRvSQL);
//         pstmt.setDate(1, sqldate);
//         pstmt.setString(2, review.getReviewContent());
//         pstmt.setInt(3, review.getRecommend());
//         pstmt.setInt(4, review.getLps().getLPSId());
//         pstmt.executeUpdate();
//
//      } catch (SQLException e) {
//         e.printStackTrace();
//      } finally {
//         MyConnection.close(con, pstmt);
//      }
//   }
//   /*페이징 소스 추가*/
//   @Override
//	public int selectAllCount() throws FindException{
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			con = MyConnection.getConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		}
//		String selectAllCountSQL = "SELECT COUNT (*) FROM review";
//		try {
//			pstmt = con.prepareStatement(selectAllCountSQL);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				int selectAllCount = rs.getInt("COUNT(*)");
//				return selectAllCount;
//			}else {
//				throw new FindException("리뷰가 없습니다.");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		}finally {
//			MyConnection.close(con,pstmt,rs);
//		}
//	}
//
//	@Override
//	public int selectAllCount(int lessonId) throws FindException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			con = MyConnection.getConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		}
//		String selectAllCountSQL = "SELECT COUNT(*) FROM review, lesson, lesson_per_student where lesson_id = ? AND lps_lesson_id = lesson_id AND lps_id = review_lps_id";
//		try {
//			pstmt = con.prepareStatement(selectAllCountSQL);
//			pstmt.setInt(1, lessonId);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				int selectAllCount = rs.getInt("COUNT(*)");
//				return selectAllCount;
//			}else {
//				throw new FindException("리뷰가 없습니다.");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		}finally {
//			MyConnection.close(con,pstmt,rs);
//		}
//	}
//	
//   @Override
//	public List<LessonReview> selectByPage(int currPage, int dataPerPage) throws FindException{
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			con = MyConnection.getConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		}
//		String selectByPageSQL = "SELECT * FROM (SELECT review_id, review_content, review_date, review_recommendation, review_lps_id ,row_number() OVER (ORDER BY review_id DESC) AS rnum FROM review) WHERE rnum BETWEEN fun_start_row(?,?) AND fun_end_row(?,?)";
//		List<LessonReview> currPageList = new ArrayList<>();
//		ILPSService lpsservice = new LPSService();
//		try {
//			pstmt = con.prepareStatement(selectByPageSQL);
//			pstmt.setInt(1, currPage);
//			pstmt.setInt(2, dataPerPage);
//			pstmt.setInt(3, currPage);
//			pstmt.setInt(4, dataPerPage);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				int review_id = rs.getInt("review_id");
//				String review_content = rs.getString("review_content");
//				Date review_date = rs.getDate("review_date");
//				int review_recommend = rs.getInt("review_recommendation");
//				int review_lps_id = rs.getInt("review_lps_id");
//				//String notice_content = rs.getString("notice_content");
//				LPS lps = lpsservice.findByLPSId(review_lps_id);
//				
//				LessonReview lessonreview = new LessonReview(review_id, review_content, review_date, review_recommend, lps);
//				currPageList.add(lessonreview);
//			}
//			if(currPageList.size()==0) {
//				throw new FindException("리뷰가 존재하지 않습니다.");
//			}
//			return currPageList;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		}finally {
//			MyConnection.close(con,pstmt,rs);
//		}
//	}
//   //2021-04-29 김재경: 주석 삭제
//   
//   @Override
//   public List<LessonReview> selectByPage(int currPage, int dataPerPage, int lessonId) throws FindException{
//      Connection con = null;
//      PreparedStatement pstmt = null;
//      ResultSet rs = null;
//      try {
//         con = MyConnection.getConnection();
//      } catch (Exception e) {
//         e.printStackTrace();
//         throw new FindException(e.getMessage());
//      }
//      String selectByPageSQL = "SELECT * FROM (SELECT review_id, review_content, review_date, review_recommendation, review_lps_id ,row_number() OVER (ORDER BY review_id DESC) AS rnum FROM review WHERE review_lps_id IN (SELECT lps_id FROM lesson_per_student where lps_lesson_id = ?)) WHERE rnum BETWEEN fun_start_row(?, ?) AND fun_end_row(?, ?)";
//      List<LessonReview> currPageList = new ArrayList<>();
//      ILPSService lpsservice = new LPSService();
////      List<LPS>lpslist = lpsservice.findByLessonId(lessonId);
//      //테스트
//      //      System.out.println(lessonId);
////      for(LPS lps:lpslist) {
////         System.out.println(lps.getLPSId());
////      }
////      }
//      try {
////         for(LPS lps:lpslist) {
////            int lpsId = lps.getLPSId();
//            pstmt = con.prepareStatement(selectByPageSQL);
//            pstmt.setInt(1, lessonId);
//            pstmt.setInt(2, currPage);
//            pstmt.setInt(3, dataPerPage);
//            pstmt.setInt(4, currPage);
//            pstmt.setInt(5, dataPerPage);
//            rs = pstmt.executeQuery();
//            while(rs.next()) {
//               int review_id = rs.getInt("review_id");
//               String review_content = rs.getString("review_content");
//               Date review_date = rs.getDate("review_date");
//               int review_recommend = rs.getInt("review_recommendation");
//               int review_lps_id = rs.getInt("review_lps_id");
//               //String notice_content = rs.getString("notice_content");
//               LPS lps1 = lpsservice.findByLPSId(review_lps_id);
//               System.out.println("오라클" + lps1);
//               
//               
//               LessonReview lessonreview = new LessonReview(review_id, review_content, review_date, review_recommend, lps1);
//               System.out.println("오라클" + lessonreview);
//               currPageList.add(lessonreview);
//            }
//            if(currPageList.size()==0) {
//               throw new FindException("리뷰가 존재하지 않습니다.");
//            }
////         }
//         return currPageList;
//      } catch (SQLException e) {
//         e.printStackTrace();
//         throw new FindException(e.getMessage());
//      }finally {
//         MyConnection.close(con,pstmt,rs);
//      }
//   }
//
//	@Override
//	public LessonReview selectById(int lpsId) throws FindException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			con = MyConnection.getConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		}
//		String selectBySQL = "SELECT * FROM review WHERE REVIEW_LPS_ID = ?";
//		ILPSService lpsservice = new LPSService();
//		LessonReview lessonreview = new LessonReview();
//		try {
//			pstmt = con.prepareStatement(selectBySQL);
//			pstmt.setInt(1, lpsId);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				int review_id = rs.getInt("review_id");
//				String review_content = rs.getString("review_content");
//				Date review_date = rs.getDate("review_date");
//				int review_recommend = rs.getInt("review_recommendation");
//				LPS lps = lpsservice.findByLPSId(lpsId);
//				lessonreview.setReviewId(review_id); // = new LessonReview(review_id, review_content, review_date, review_recommend, lps);
//				lessonreview.setReviewContent(review_content);
//				lessonreview.setReviewDate(review_date);
//				lessonreview.setRecommend(review_recommend);
//				lessonreview.setLps(lps);
//			}
//			return lessonreview;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		}finally {
//			MyConnection.close(con,pstmt,rs);
//		}
//	}
//
	//2021-04-29 메인 테스트 코드 삭제
	 /*페이징 소스 추가 끝*/
//	public static void main(String args[]) {
//		LessonReviewDAO lrl = new LessonReviewDAOOracle();
//		LPSDAO dao = new LPSDAOOracle();
//		try {
//			LessonReview lr = lrl.selectById(22);
//			System.out.println(lr.getReviewId());
//			List<LPS> lpslist = dao.selectByLessonId(7);
//			System.out.println(lpslist);
//			List<LessonReview>list = lrl.selectByPage(1, 10, 7);
//			for(LessonReview review: list) {
//				System.out.println(review.getLps().getLPSId());
//			}
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
//	}
}