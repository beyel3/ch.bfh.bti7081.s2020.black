package ch.bfh.bti7081.s2020.black.presenters;

import java.util.List;

import ch.bfh.bti7081.s2020.black.model.Coreuser;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.eventStateModel.Create;
import ch.bfh.bti7081.s2020.black.views.CreateEventViewImplementaion;

public class CreateEventPresenter {

	private CreateEventViewImplementaion view;
	private Create model;
	private EventTemplate eventTemplate;
	
	public CreateEventPresenter(CreateEventViewImplementaion eventCreaterViewImplementaion) {

		this.view = eventCreaterViewImplementaion;
		this.model = new Create();
		
	}
	
	public void saveEvent(boolean isPublic, int maxParicipants, List<Coreuser> participants) {
		
		Event event = new Event(eventTemplate, isPublic, maxParicipants, participants);
		
			model.addEventToTemplate(event);
			//model.saveEvent(eventTemplate);

	}

	public void saveEventWithTemplate(EventTemplate eventTemplate, boolean isPublic, int maxParicipants,
			List<Coreuser> participatns) {
		
	}

//
//	public EventTemplate getEventTemplateFromID(int eventTemplateID) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public void setEventTemplateByURLParameter(String parameter) {
		
		int eventTemplateID = Integer.parseInt(parameter);
		this.eventTemplate = model.getEventTemplateByID(eventTemplateID);
		view.setTemplateInfo(eventTemplate.getTitle(), eventTemplate.getDescription(), eventTemplate.getTags());
		
	}

	public void saveEvent(Boolean value, int round) {
		// TODO Auto-generated method stub
		
	}

}
