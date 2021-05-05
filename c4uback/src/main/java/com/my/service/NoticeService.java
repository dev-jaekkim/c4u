package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.NoticeDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Notice;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class NoticeService implements INoticeService{
	
	@Autowired
	private NoticeDAO dao;

	@Override
	public List<Notice> findPerPage(int currentPage, int cnt_per_page) throws FindException {
		return dao.selectPerPage(currentPage, cnt_per_page);
	}

	@Override
	public int findCnt() throws FindException {
		return dao.selectCnt();
	}
	
	@Override
	public int findCnt(String notice_title) throws FindException {
		return dao.selectCnt(notice_title);
	}

	@Override
	public List<Notice> findByTitlePerPage(String notice_title, int currentPage, int cnt_per_page)
			throws FindException {
		return dao.selectByTitlePerPage(notice_title, currentPage, cnt_per_page);
	}

	@Override
	public Notice findById(int notice_id) throws FindException {
		return dao.selectById(notice_id);
	}

	@Override
	public Notice modify(Notice notice) throws ModifyException {
		return dao.update(notice);
	}

	@Override
	public int remove(int notice_id) throws RemoveException {
		return dao.delete(notice_id);
	}

	@Override
	public void add(Notice notice) throws AddException {
		dao.insert(notice);
	}
}