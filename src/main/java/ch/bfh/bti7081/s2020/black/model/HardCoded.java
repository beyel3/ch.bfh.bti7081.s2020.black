package ch.bfh.bti7081.s2020.black.model;

import java.util.ArrayList;
import java.util.List;

public class HardCoded {
	
	private ArrayList<Tag> tags;
	private ArrayList<EventTemplate> eventTemplates;
	private ArrayList<Event> events;
	
	public HardCoded() {
		
		this.tags = new ArrayList<Tag>();
		this.eventTemplates = new ArrayList<EventTemplate>();
		this.events = new ArrayList<Event>();
		
		tags.add(new Tag(1, "#outdoor"));
		tags.add(new Tag(2, "#sporty"));
		tags.add(new Tag(3, "#indoor"));
		tags.add(new Tag(4, "#chill"));
		
		
		EventTemplate hardOne = new EventTemplate(
				1, 
				"hike", 
				"hiking in the swiss alps",
				null, 
				null, 8.2);
		
		ArrayList<Tag> sublistOne = new ArrayList<Tag>();
		sublistOne.add(tags.get(0));
		sublistOne.add(tags.get(1));
		
		hardOne.setTags(sublistOne);
		eventTemplates.add(hardOne);
		
		
		EventTemplate hardTwo = new EventTemplate(
				2,
				"cook dinner",
				"Invite your friends to this activity and let them participate in cooking",
				null, 
				null, 6.7);
				
		ArrayList<Tag> sublistTwo = new ArrayList<Tag>();
		sublistTwo.add(tags.get(2));
		sublistTwo.add(tags.get(3));
				
		hardTwo.setTags(sublistTwo);
		eventTemplates.add(hardTwo);
		
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}
	
	public ArrayList<EventTemplate> getEventTemplates(){
		return eventTemplates;
	}

	public EventTemplate getEventTemplateFromID(int eventTemplateID) {
		return eventTemplates.get(eventTemplateID);
	}
}
