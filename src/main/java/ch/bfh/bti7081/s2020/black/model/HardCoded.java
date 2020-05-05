package ch.bfh.bti7081.s2020.black.model;

import java.util.ArrayList;
import java.util.List;

public class HardCoded {
	
	private List<Tag> tags;
	private List<EventTemplate> eventTemplates;
	private List<Event> events;
	
	public HardCoded() {
		
		this.tags = new ArrayList<Tag>();
		this.eventTemplates = new ArrayList<EventTemplate>();
		this.events = new ArrayList<Event>();
		
		tags.add(new Tag(1, "#outdoor"));
		tags.add(new Tag(2, "#sporty"));
		tags.add(new Tag(3, "#indoor"));
		tags.add(new Tag(4, "#chill"));
		
		
		eventTemplates.add(new EventTemplate(
				1, 
				"hike", 
				"hiking in the swiss alps",
				tags, 
				null));
		
		eventTemplates.add(new EventTemplate(
				2,
				"cook dinner with friends",
				"Invite your relative to this activity and let them participate in cooking",
				tags, 
				null));
		
	}

	public List<Tag> getTags() {
		return this.tags;
	}
	
}
