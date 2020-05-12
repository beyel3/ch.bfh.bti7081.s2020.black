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
	
	public Event(int id, EventTemplate eventTemplate, String info, boolean isPublic, int rating, Status status, int pictureID, List<Coreuser> participants) {
		
		this.id = id;
		this.eventTemplate = eventTemplate;
		this.info = "";
		this.isPublic = isPublic;
		this.rating = rating;
		this.status = status;
		this.pictureId = pictureID;		
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public List<Coreuser> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Coreuser> participants) {
		this.participants = participants;
	}

	public int getPictureID() {
		return pictureId;
	}

	public void setPictureID(int pictureID) {
		this.pictureId = pictureID;
	}

	public Status getStatus() {
		return status;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
