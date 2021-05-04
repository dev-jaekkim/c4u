package com.my.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;




public class LessonPenaltyStatus {
	private int lessonPsId;
	private Lesson lesson;
	private LessonPenalty lessonPenalty;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date lessonPsDate;
	
	public LessonPenaltyStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LessonPenaltyStatus(int lessonPsId, Lesson lesson, LessonPenalty lessonPenalty, Date lessonPsDate) {
		super();
		this.lessonPsId = lessonPsId;
		this.lesson = lesson;
		this.lessonPenalty = lessonPenalty;
		this.lessonPsDate = lessonPsDate;
	}

	public int getLessonPsId() {
		return lessonPsId;
	}

	public void setLessonPsId(int lessonPsId) {
		this.lessonPsId = lessonPsId;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public LessonPenalty getLessonPenalty() {
		return lessonPenalty;
	}

	public void setLessonPenalty(LessonPenalty lessonPenalty) {
		this.lessonPenalty = lessonPenalty;
	}

	public Date getLessonPsDate() {
		return lessonPsDate;
	}

	public void setLessonPsDate(Date lessonPsDate) {
		this.lessonPsDate = lessonPsDate;
	}

	@Override
	public String toString() {
		return "LessonPenaltyStatus [lessonPsId=" + lessonPsId + ", lesson=" + lesson + ", lessonPenalty="
				+ lessonPenalty + ", lessonPsDate=" + lessonPsDate + "]";
	}
	
}
