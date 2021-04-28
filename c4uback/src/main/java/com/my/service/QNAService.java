package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.QNADAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.QNA;
import com.my.vo.Student;

@Service
public class QNAService implements IQNAService {
	
	@Autowired
	private QNADAO dao;
	
	@Override
	public List<QNA> findPerPage(int currentPage, int cnt_per_page) throws FindException {
		return dao.selectPerPage(currentPage, cnt_per_page);
	}

	@Override
	public int findCnt() throws FindException {
		return dao.selectCnt();
	}

	@Override
	public List<QNA> findByStudentIdPerPage(int student_id, int currentPage, int cnt_per_page) throws FindException {
		return dao.selectByStudentIdPerPage(student_id, currentPage, cnt_per_page);
	}

	@Override
	public List<QNA> findByNameOrTitleOrContentPerPage(String word, int currentPage, int cnt_per_page)
			throws FindException {
		return dao.selectByNameOrTitleOrContentPerPage(word, currentPage, cnt_per_page);
	}

	@Override
	public QNA findById(int qna_id) throws FindException {
		return dao.selectById(qna_id);
	}
	
	@Override
	public QNA findById(int qna_id, Student student) throws FindException {
		return dao.selectById(qna_id, student);
	}


	@Override
	public void add(QNA qna) throws AddException {
		dao.insert(qna);
	}

	@Override
	public QNA modify(QNA qna) throws ModifyException {
		return dao.update(qna);
	}

	@Override
	public int remove(int qna_id) throws RemoveException {
		return dao.delete(qna_id);
	}

}
