package ch.bfh.bti7081.s2020.black.model;

import java.util.Calendar;
import java.util.Date;

public class Post {
	
	private String message;
	private Date date;
	private Coreuser coreuser;
	
	//get from DB
	public Post(String message, Date date, Coreuser coreuser) {
		this.message = message;
		this.date = date;
		this.coreuser = coreuser;
	}
	
	//gererated by user
	public Post(String message, Coreuser coreuser) {
		this.message = message;
		this.date = Calendar.getInstance().getTime();
		this.coreuser = coreuser;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getTimeString() {
		return date.toString();
	}

}
