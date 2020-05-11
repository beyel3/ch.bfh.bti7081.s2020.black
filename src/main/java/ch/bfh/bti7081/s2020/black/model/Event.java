package ch.bfh.bti7081.s2020.black.model;

import java.util.List;

public class Event {
	
	private int id;
	private EventTemplate eventTemplate;
	private String info;
	private boolean isPublic;
	private List<Coreuser> participants;
	private Status status;
	private int rating;
	private int pictureId;
	
	public Event(int id, EventTemplate eventTemplate, String info, List<Coreuser> participants) {
		
		this.id = id;
		this.eventTemplate = eventTemplate;
		this.info = "";
		this.rating = 0;
		this.status = Status.open;
		this.pictureId = 0;		
		this.participants = participants;
	}

	public void addPicture(int pictureId){
		this.pictureId = pictureId;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

	public int getRating() {
		return this.rating;
	}
}
