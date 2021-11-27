package com.jordypiedra.demo.entity;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean(String message) {
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("HelloWorldBean [message=%s]", message);
	}

}
