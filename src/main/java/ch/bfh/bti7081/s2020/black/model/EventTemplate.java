package ch.bfh.bti7081.s2020.black.model;

import java.util.Collection;
import java.util.List;

public class EventTemplate {
	
	private int id;
	private String title;
	private String description;
	private List<Tag> tags;
	private List<Event> events;
	private double avgRating;
	
	public EventTemplate(int id, String title, String description, List<Tag> tags, List<Event> events) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.tags = tags;
		this.events = events;
		this.avgRating = 10;		
		
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

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public Collection<Tag> getTags() {
		return this.tags;
	}

	public double getRating() {
		return this.avgRating;
	}

	public String getTemplateIDforURL() {
		return Integer.toString(id);
	}

}
