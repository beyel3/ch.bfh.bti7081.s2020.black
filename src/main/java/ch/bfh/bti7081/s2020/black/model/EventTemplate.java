package ch.bfh.bti7081.s2020.black.model;

import java.util.ArrayList;
import java.util.List;

public class EventTemplate {
	
	private int id;
	private String title;
	private String description;
	private ArrayList<Tag> tags;
	private double avgRating;
	
	// generate from persistence
	public EventTemplate(int id, String title, String description, ArrayList<Tag> tags, double avgRating) {
		
		this.id = id;
		this.title = title;
		this.description = description;
		this.tags = tags;
		this.avgRating = avgRating;	
		
	}
	
	// generate new by presenter
	public EventTemplate(String title, String description, ArrayList<Tag> tags) {
		
		this.title = title;
		this.description = description;
		this.tags = tags;
		
	}

	
	public String toString() {
		
		String res = "{";
		
		for(Tag t : tags) {
			res += t.getTagName() + ", ";
		}
		
		res += "}";
		
		return id + " " + title + " " + res;
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
