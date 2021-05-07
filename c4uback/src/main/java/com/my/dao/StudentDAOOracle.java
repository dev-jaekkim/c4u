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
import com.my.vo.Penalty;
import com.my.vo.PenaltyStatus;
import com.my.vo.Student;

@Repository
public class StudentDAOOracle implements StudentDAO {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	

	@Override
	public List<Student> selectStudentList(int currentPage, int cnt_per_page, String word) throws FindException {
		SqlSession session = null;
		try { 
			session = sqlSessionFactory.openSession();
			List<Student> list = null;
			if(word == null) {
				Map<String, Object> map = new HashMap<>();
				map.put("currentPage", currentPage);
				map.put("cnt_per_page", cnt_per_page);
				list = session.selectList("mybatis.StudentMapper.selectStudentList",map);
				if(list.size()==0) {
					throw new FindException("해당 학생정보가 존재하지 않습니다.");
				}
				return list;
			}else {
				Map<String, Object> map = new HashMap<>();
				map.put("currentPage", currentPage);
				map.put("cnt_per_page", cnt_per_page);
				map.put("word", word);
				list = session.selectList("mybatis.StudentMapper.selectByEmailOrNameOrPhone", map);
				if(list.size()==0) {
					throw new FindException("해당 학생정보가 존재하지 않습니다.");
				}
				return list;
			}
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}
	
	@Override
	public int selectCnt() throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int count = session.selectOne("mybatis.StudentMapper.selectCnt");
			return count;
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public Student selectById(int student_id) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Student student = session.selectOne("mybatis.StudentMapper.selectById", student_id);
			session.commit();
			if(student == null ) {
				throw new FindException("회원 정보가 존재하지 않습니다.");
			}
			return student;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public Student selectByEmail(String studentEmail) throws FindException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Student student = session.selectOne("mybatis.StudentMapper.selectByEmail", studentEmail);
			if(student == null) {
				throw new FindException("회원 정보가 존재하지 않습니다.");
			}
			return student;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public Student selectByName(String name) throws FindException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Student student = session.selectOne("mybatis.StudentMapper.selectByName", name);
			if(student == null) {
				throw new FindException("회원 정보가 존재하지 않습니다.");
			}
			return student;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public Student selectByPwd(String inputPwd) throws FindException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Student student = session.selectOne("mybatis.StudentMapper.selectByPwd", inputPwd);
			session.commit();
			if(student == null) {
				throw new FindException("해당 정보를 찾을 수 없습니다.");
			}
			return student;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}
	
	@Override
	public List<Penalty> selectPenaltyAll() throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<Penalty> list = session.selectList("mybatis.StudentMapper.selectPenaltyAll");
			session.commit();
			if(list == null) {
				throw new FindException("해당 정보를 찾을 수 없습니다.");
			}
			return list;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	//닉네임 중복체크
	//이메일 중복체크
	@Override
	public void insert(Student s) throws AddException {
		SqlSession session = null;
		try { 
			session = sqlSessionFactory.openSession();
			session.insert("mybatis.StudentMapper.insert", s);
			session.commit();
		}catch(Exception e) {
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public void insertPenaltyStatus(PenaltyStatus ps) throws AddException {
		SqlSession session = null;
		try { 
			session = sqlSessionFactory.openSession();
			session.insert("mybatis.PenaltyStatusMapper.insertPenaltyStatus", ps);
			session.commit();
		}catch(Exception e) {
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public void update(Student s, String inputPwd) throws ModifyException {
		SqlSession session = null;
		try { 
			session = sqlSessionFactory.openSession();
			try { 
				Student originalstu = selectByEmail(s.getStudentEmail());
				if(!(originalstu.getStudentPwd().equals(inputPwd))) {
					throw new ModifyException("비밀번호가 일치하지 않습니다.");
				}else {
					Map<String, Object> map = new HashMap<>();
					map.put("student", s);
					map.put("inputPwd", inputPwd);
					session.update("mybatis.StudentMapper.update", map);
				}
			}catch(FindException e) {
				throw new FindException(e.getMessage());
			}
		}catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public void update(Student s) throws ModifyException {
	}

	@Override
	public void updateStatus(int student_id) throws ModifyException {
		SqlSession session = null;
		try { 
			session = sqlSessionFactory.openSession();
			session.update("mybatis.StudentMapper.updateStatus", student_id);
			session.commit();
		}catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	@Override
	public void updateAdminPwd(Student student, String certifyPwd) throws ModifyException {
		SqlSession session = null;
		try { 
			session = sqlSessionFactory.openSession();
			Student admin = selectByEmail("admin@admin.com");
			if(!(admin.getStudentPwd() == certifyPwd)) {
				session.insert("mybatis.StudentMapper.updateAdminPwd", student);
			}else {
				throw new ModifyException("비밀번호가 일치하지 않습니다.");
			}
			session.commit();
		}catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) session.close();
		}
	}

	


}