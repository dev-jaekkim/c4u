package com.my.vo;

import java.util.Date;

import org.springframework.stereotype.Component;
<<<<<<< HEAD

<<<<<<< HEAD
=======
@Component
>>>>>>> c7f5792f746e574402b274d284f43a24c5b94014
=======
@Component
>>>>>>> parent of cce0371 (canyi 2021-04-30 update)
public class LessonPenaltyStatus {
	private int lessonPsId;
	private Lesson lesson;
	private LessonPenalty lessonPenalty;
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
