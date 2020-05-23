package ch.bfh.bti7081.s2020.black.model;

import java.util.Calendar;
import java.util.Date;

public class Post {
	
	private String message;
	private Date date;
	private Account account;
	
	//get from DB
	public Post(String message, Date date, Account account) {
		this.message = message;
		this.date = date;
		this.account = account;
	}
	
	//gererated by user
	public Post(String message, Account coreuser) {
		this.message = message;
		this.date = Calendar.getInstance().getTime();
		this.account = coreuser;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getTimeString() {
		return date.toString();
	}

}
