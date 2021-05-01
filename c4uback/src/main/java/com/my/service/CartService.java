package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.CartDAO;
import com.my.dao.CartDAOOracle;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.RemoveException;
import com.my.vo.Cart;
import com.my.vo.Lesson;

@Service
public class CartService implements ICartService {
	
	@Autowired
	CartDAO dao = new CartDAOOracle();
	
	@Override
	public List<Lesson> findById(int studentId) throws FindException {
		return dao.selectById(studentId);
	}

	@Override
	public Cart findById(int lessonId, int studentId) throws FindException {
		return dao.selectById(lessonId, studentId);
	}
	
	@Override
	public void add(int lessonId, int studentId) throws AddException {
		dao.insert(lessonId, studentId);
	}

	@Override
	public void remove(int lessonId, int studentId) throws RemoveException {
		 dao.delete(lessonId, studentId);
	}

	@Override
	public int findAllCount() throws FindException {
		return dao.selectAllCount();
	}

	@Override
	public int findAllCount(int studentId) throws FindException {
		return dao.selectAllCount(studentId);
		
	}

	@Override
	public List<Lesson> findByPage(int cnt_per_page, int currentPage, int studentId) throws FindException {

		return dao.selectByPage(cnt_per_page, currentPage, studentId);
	}
}
