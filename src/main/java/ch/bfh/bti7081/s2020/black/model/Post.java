package ch.bfh.bti7081.s2020.black.model;

import java.util.Calendar;
import java.util.Date;

public class Post {
	
	private String message;
	private Event event;
	private Date date;
	private Account account;
	
	//get from DB
	public Post(String message, Event event, Date date, Account account) {
		this.message = message;
		this.date = date;
		this.account = account;
		this.event = event;
	}
	
	//gererated by user
	public Post(String message, Event event, Account coreuser) {
		this.message = message;
		this.date = new Date();
		this.account = coreuser;
		this.event = event;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getTimeString() {
		return date.toString();
	}
	
	public String getUser() {
		return account.toString();
	}

	public Date getDate(){ return this.date;}

}
