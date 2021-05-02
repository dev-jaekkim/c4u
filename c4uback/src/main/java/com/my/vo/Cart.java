package com.my.vo;

public class Cart {
	private int cartId;
	private Lesson lesson;
	private Student student;
	private int rnum;
	
	public Cart() {
		super();
	
	}
	
	
	public Cart(int cartId, Lesson lesson, Student student, int rnum) {
		super();
		this.cartId = cartId;
		this.lesson = lesson;
		this.student = student;
		this.rnum = rnum;
	}


	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", lesson=" + lesson + ", student=" + student + ", rnum=" + rnum + "]";
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
}