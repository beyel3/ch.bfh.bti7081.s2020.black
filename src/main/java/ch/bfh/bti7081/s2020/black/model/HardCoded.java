package ch.bfh.bti7081.s2020.black.model;

import java.util.ArrayList;
import java.util.List;

public class HardCoded {
	
	private ArrayList<Tag> tags;
	private ArrayList<EventTemplate> eventTemplates;
	private ArrayList<Event> events;
	private ArrayList<Coreuser> participants;
	
	public HardCoded() {
		
		this.tags = new ArrayList<Tag>();
		this.eventTemplates = new ArrayList<EventTemplate>();
		this.events = new ArrayList<Event>();
		this.participants = new ArrayList<Coreuser>();
		
		participants.add(new Patient("Mario", "Schläppi", "schl@bfh.ch"));
		participants.add(new Patient("Luca", "Beyeler", "bey@bfh.ch"));
		participants.add(new Patient("Ramon", "Luginbühl", "lug@bfh.ch"));
		participants.add(new Patient("Robin", "Füglister", "fuegi@bfh.ch")); 
		
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
		
		EventTemplate hardThree = new EventTemplate(
				3, 
				"hike", 
				"hiking in the swiss alps",
				null, 
				null, 8.2);
		
		ArrayList<Tag> sublistThree = new ArrayList<Tag>();
		sublistThree.add(tags.get(0));
		sublistThree.add(tags.get(1));
		
		hardThree.setTags(sublistThree);
		eventTemplates.add(hardThree);
		
		EventTemplate hardFour = new EventTemplate(
				4, 
				"hike", 
				"hiking in the swiss alps",
				null, 
				null, 8.2);
		
		ArrayList<Tag> sublistFour = new ArrayList<Tag>();
		sublistFour.add(tags.get(0));
		sublistFour.add(tags.get(1));
		
		hardFour.setTags(sublistFour);
		eventTemplates.add(hardFour);
		
		EventTemplate hardFive = new EventTemplate(
				5, 
				"hike", 
				"hiking in the swiss alps",
				null, 
				null, 8.2);
		
		ArrayList<Tag> sublistFive = new ArrayList<Tag>();
		sublistFive.add(tags.get(0));
		sublistFive.add(tags.get(1));
		
		hardFive.setTags(sublistFive);
		eventTemplates.add(hardFive);
		

		
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

	public void addEventTemplate(EventTemplate eventTemplate) {
		eventTemplates.add(eventTemplate);
		eventTemplates.get(eventTemplates.size()-1).setId(eventTemplates.size());;
	}

	public ArrayList<Coreuser> getCoreUser() {
		return participants;
	}
	
	public ArrayList<Event> getEvent() {
		return events;
	}
}
