package com.my.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QNA {
	private int qnaId;
	private String qnaTitle;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date qnaDate;
	private String qnaContent;
	private String qnaComment;
	private Student student;
	
	public QNA() {
		super();
	}
	
	public QNA(int qnaId, String qnaTitle, Date qnaDate, String qnaContent, Student student) {
		super();
		this.qnaId = qnaId;
		this.qnaTitle = qnaTitle;
		this.qnaDate = qnaDate;
		this.qnaContent = qnaContent;
		this.student = student;
	}
	public int getQnaId() {
		return qnaId;
	}
	public void setQnaId(int qnaId) {
		this.qnaId = qnaId;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public Date getQnaDate() {
		return qnaDate;
	}
	public void setQnaDate(Date qnaDate) {
		this.qnaDate = qnaDate;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public String getQnaComment() {
		return qnaComment;
	}
	public void setQnaComment(String qnaComment) {
		this.qnaComment = qnaComment;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "QNA [qnaId=" + qnaId + ", qnaTitle=" + qnaTitle + ", qnaContent=" + qnaContent + ", qnaComment="
				+ qnaComment + "]";
	}
}
