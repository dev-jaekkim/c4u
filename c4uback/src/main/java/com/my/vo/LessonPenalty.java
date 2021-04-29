package com.my.vo;

import org.springframework.stereotype.Component;

@Component
public class LessonPenalty {
	private int lpId;
	private String lpContent;
	
	public LessonPenalty() {
		super();
	}
	public LessonPenalty(int lpId, String lpContent) {
		super();
		this.lpId = lpId;
		this.lpContent = lpContent;
	}
	public int getLpId() {
		return lpId;
	}
	public void setLpId(int lpId) {
		this.lpId = lpId;
	}
	public String getLpContent() {
		return lpContent;
	}
	public void setLpContent(String lpContent) {
		this.lpContent = lpContent;
	}
	@Override
	public String toString() {
		return "LessonPenalty [lpId=" + lpId + ", lpContent=" + lpContent + "]";
	}
	
	
}
