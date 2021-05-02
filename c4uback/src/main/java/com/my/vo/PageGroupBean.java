package com.my.vo;

import java.util.List;

import lombok.extern.log4j.Log4j;

@Log4j
public class PageGroupBean<T> {
	private int cnt_per_page = 10; //페이지별 보여줄 목록 수 
	private int cnt_per_page_group=10; //페이지 그룹에 보여줄 페이지 수 
	private int totalCnt; //총 목록 수 
	private int startPage; //시작 페이지
	private int endPage; //끝페이지
	private int currentPage; //현재페이지
	private int totalPage; //총 페이지 수
	private List<T> list;

	public PageGroupBean() {
		super();
	}

	public PageGroupBean(int totalCnt, int currentPage, List<T> list) {
		this(totalCnt, currentPage, list, 10, 10);
	}
	public PageGroupBean(int totalCnt, 
			int currentPage, 
			List<T> list, 
			int cnt_per_page, 
			int cnt_per_page_group) {
		this.totalCnt = totalCnt;
		this.currentPage = currentPage;
		this.list = list;
		this.cnt_per_page = cnt_per_page;
		this.cnt_per_page_group = cnt_per_page_group;

		this.totalPage = (int)Math.ceil((double)totalCnt/cnt_per_page); //총페이지수 계산하기
		log.info("총게시물수:" + totalCnt + "총페이지수:" + totalPage);

		startPage = ((currentPage-1)/cnt_per_page_group)*cnt_per_page_group+1;
		endPage = startPage+cnt_per_page_group-1;
		if(totalPage<endPage) {
			endPage=totalPage;
		}
	}

	public int getCnt_per_page() {
		return cnt_per_page;
	}

	public void setCnt_per_page(int cnt_per_page) {
		this.cnt_per_page = cnt_per_page;
	}
	public int getCnt_per_page_group() {
		return cnt_per_page_group;
	}
	public void setCnt_per_page_group(int cnt_per_page_group) {
		this.cnt_per_page_group = cnt_per_page_group;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	@Override
	public String toString() {
		return "PageGroupBean [cnt_per_page=" + cnt_per_page + ", cnt_per_page_group=" + cnt_per_page_group
				+ ", totalCnt=" + totalCnt + ", startPage=" + startPage + ", endPage=" + endPage + ", currentPage="
				+ currentPage + ", totalPage=" + totalPage + ", list=" + list + "]";
	}

	public void setList(List<T> list) {
		this.list = list;
	}



}
