package ch.bfh.bti7081.s2020.black.presenters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.RouterLayout;

import ch.bfh.bti7081.s2020.black.model.Coreuser;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;
import ch.bfh.bti7081.s2020.black.views.EventCreaterView;

import static ch.bfh.bti7081.s2020.black.views.MainView.hardCoded;

public class EventCreaterPresenter implements RouterLayout, EventCreaterView.EventCreaterViewListener{

	private Persistence persistence;
	public EventCreaterPresenter() {

		try {
			persistence = new Persistence();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void saveEventCreateNewEventTemplate(String title, String description, ArrayList<Tag> tags, boolean isPublic,
			int maxParicipants, List<Coreuser> participatns) {
		
		EventTemplate eventTemplate = new EventTemplate(title, description, tags, null, Double.valueOf(0));
		Event event = new Event(eventTemplate, isPublic, maxParicipants,null);
		
		ArrayList<Event> events = new ArrayList<Event>();
		events.add(event);
		event.getEventTemplate().setEvents(events);
		
		hardCoded.addEventTemplate(eventTemplate);
		
	}

	@Override
	public void saveEventWithTemplate(EventTemplate eventTemplate, boolean isPublic, int maxParicipants,
			List<Coreuser> participatns) {
		
		Event event = new Event(eventTemplate, isPublic, maxParicipants, null);
		eventTemplate.getEvents().add(event);
		
	}

	public Collection<Tag> getTags() {
	
//		return persistence.getTags();
		return hardCoded.getTags();
	}

	public List<EventTemplate> getEventTemplates() {
//		return persistence.getEventTemplateList();
		return hardCoded.getEventTemplates();
	}

	@Override
	public Element getElement() {
		// TODO Auto-generated method stub
		return null;
	}

	public EventTemplate getEventTemplateFromID(int eventTemplateID) {
//		return persistence.getEventTemplate(eventTemplateID);
		return hardCoded.getEventTemplateFromID(eventTemplateID-1);
	}
	
}
