package Model;

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
		this.avgRating = 0;		
		
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

}
