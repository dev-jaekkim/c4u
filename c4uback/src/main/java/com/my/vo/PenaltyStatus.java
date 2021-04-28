package com.my.vo;

import java.util.Date;

public class PenaltyStatus {
	private int psId;
	private Student student;
	private Penalty penalty;
	private Date psDate;
	public PenaltyStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PenaltyStatus(int psId, Student student, Penalty penalty, Date psDate) {
		super();
		this.psId = psId;
		this.student = student;
		this.penalty = penalty;
		this.psDate = psDate;
	}
	public int getPsId() {
		return psId;
	}
	public void setPsId(int psId) {
		this.psId = psId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Penalty getPenalty() {
		return penalty;
	}
	public void setPenalty(Penalty penalty) {
		this.penalty = penalty;
	}
	public Date getPsDate() {
		return psDate;
	}
	public void setPsDate(Date psDate) {
		this.psDate = psDate;
	}
	@Override
	public String toString() {
		return "PenaltyStatus [psId=" + psId + ", student=" + student + ", penalty=" + penalty + ", psDate=" + psDate
				+ "]";
	}
	
	

}
