package ch.bfh.bti7081.s2020.black.model;

import java.util.ArrayList;
import java.util.List;

public class Event {
	
	private int id;
	private EventTemplate eventTemplate;
	private ArrayList<Post> posts;
 	private boolean isPublic;
 	private int maxParticipants;
	private List<Coreuser> participants;
	private Status status;
	private int rating;
	private int pictureId;
	
	//Persistence Constructor
	public Event(int id, EventTemplate eventTemplate, ArrayList<Post> posts, boolean isPublic, int maxParticipants, int rating, Status status, int pictureID, List<Coreuser> participants) {
		
		this.id = id;
		this.eventTemplate = eventTemplate;
		this.posts = posts;
		this.isPublic = isPublic;
		this.maxParticipants = maxParticipants;
		this.rating = rating;
		this.status = status;
		this.pictureId = pictureID;		
		this.participants = participants;
		
	}
	
	//User Constructor
	public Event(EventTemplate eventTemplate, boolean isPublic, int maxParticipants, List<Coreuser> participants) {
		
		this.id = 0;
		this.eventTemplate = eventTemplate;
		this.posts = null;
		this.isPublic = isPublic;
		this.maxParticipants = maxParticipants;
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

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}
}
