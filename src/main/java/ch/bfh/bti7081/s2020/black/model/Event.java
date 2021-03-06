package ch.bfh.bti7081.s2020.black.model;

import java.util.ArrayList;

public class Event {
	
	private int id;
	private EventTemplate eventTemplate;
	private ArrayList<Post> posts;
 	private boolean isPublic;
 	private int maxParticipants;
	private ArrayList<Account> participants;
	private Status status;
	private int rating;
	
	//Persistence Constructor
	public Event(int id, EventTemplate eventTemplate, ArrayList<Post> posts, boolean isPublic, int maxParticipants, int rating, Status status, ArrayList<Account> participants) {
		
		this.id = id;
		this.eventTemplate = eventTemplate;
		this.posts = posts;
		this.isPublic = isPublic;
		this.maxParticipants = maxParticipants;
		this.rating = rating;
		this.status = status;	
		this.participants = participants;
		
	}
	
	//User Constructor
	public Event(EventTemplate eventTemplate, boolean isPublic, int maxParticipants, ArrayList<Account> participants) {
		
		this.id = 0;
		this.eventTemplate = eventTemplate;
		this.posts = new ArrayList<Post>();
		this.isPublic = isPublic;
		this.maxParticipants = maxParticipants;
		this.rating = 0;
		this.status = Status.open;	
		this.participants = participants;
		
	}
	
	public String toString() {
		String part = "";
		for(Account a : participants) {
			part += a.getFirstName();
		}
		
		return "ID: " + id + ", " + "TEMPLATE: " + eventTemplate.getTitle() + ", " + part;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

	public int getRating() {
		return this.rating;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EventTemplate getEventTemplate() {
		return eventTemplate;
	}

	public void setEventTemplate(EventTemplate eventTemplate) {
		this.eventTemplate = eventTemplate;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public ArrayList<Account> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<Account> participants) {
		this.participants = participants;
	}

	public Status getStatus() {
		return status;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}

}
