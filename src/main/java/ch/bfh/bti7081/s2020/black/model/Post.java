package ch.bfh.bti7081.s2020.black.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Post {
	
	private String message;
	private Event event;
	private Timestamp timeStamp;
	private Account account;
	
	//get from DB
	public Post(String message, Event event, Timestamp timeStamp, Account account) {
		this.message = message;
		this.timeStamp = timeStamp;
		this.account = account;
		this.event = event;
	}
	
	//gererated by user
	public Post(String message, Event event, Account coreuser) {
		this.message = message;
		this.timeStamp = new Timestamp(System.currentTimeMillis());
		this.account = coreuser;
		this.event = event;
	}
	
	public String getDateString() {
		return "Test DB";
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getTimeString() {
		return timeStamp.toString();
	}
	
	public String getUserName() {
		return account.toString();
	}

	public Timestamp getDate(){ 
		return this.timeStamp;
		}

	public int getAccountID() {
		return account.getId();
	}

}
