package ch.bfh.bti7081.s2020.black.model;

import java.util.ArrayList;
import java.util.List;

public class EventTemplate {
	
	private int id;
	private String title;
	private String description;
	private ArrayList<Tag> tags;
	private ArrayList<Event> events;
	private double avgRating;
	
	public EventTemplate(int id, String title, String description, ArrayList<Tag> tags, ArrayList<Event> events, double avgRating) {
		
		this.id = id;
		this.title = title;
		this.description = description;
		this.tags = tags;
		this.events = events;
		this.avgRating = avgRating;	
		
	}
	
	public EventTemplate(String title, String description, ArrayList<Tag> tags, ArrayList<Event> events, double avgRating) {
		
		this.title = title;
		this.description = description;
		this.tags = tags;
		this.events = events;
		this.avgRating = avgRating;	
		
	}


	public void rate(int rating) {

		double count = 0;
		double sum = 0;
		for (Event e : events) {
			if (e.getRating() != 0) {
				sum += e.getRating();
				count++;
			}
		}
		this.avgRating = sum/count;
		
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public String getTemplateIDforURL() {
		return Integer.toString(id);
	}

}
