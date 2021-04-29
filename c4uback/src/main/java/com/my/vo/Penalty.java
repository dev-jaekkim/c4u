package com.my.vo;

import org.springframework.stereotype.Component;

@Component
public class Penalty {
	private int penaltyId;
	private String penaltyContent;
	public Penalty() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Penalty(int penaltyId, String penaltyContent) {
		super();
		this.penaltyId = penaltyId;
		this.penaltyContent = penaltyContent;
	}
	public int getPenaltyId() {
		return penaltyId;
	}
	public void setPenaltyId(int penaltyId) {
		this.penaltyId = penaltyId;
	}
	public String getPenaltyContent() {
		return penaltyContent;
	}
	public void setPenaltyContent(String penaltyContent) {
		this.penaltyContent = penaltyContent;
	}
	@Override
	public String toString() {
		return "Penalty [penaltyId=" + penaltyId + ", penaltyContent=" + penaltyContent + "]";
	}
	
	
	
}
