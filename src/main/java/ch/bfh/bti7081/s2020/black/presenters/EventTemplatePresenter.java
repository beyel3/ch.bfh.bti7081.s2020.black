package ch.bfh.bti7081.s2020.black.presenters;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2020.black.model.Coreuser;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.model.eventStateModel.ChooseTemplate;
import ch.bfh.bti7081.s2020.black.views.EventTemplateViewImplementation;

public class EventTemplatePresenter extends Presenter {
		
		private ChooseTemplate model;
		private EventTemplateViewImplementation view;

	public EventTemplatePresenter(EventTemplateViewImplementation eventTemplateViewImplementation) {
		this.model = new ChooseTemplate();
		this.view = eventTemplateViewImplementation;
	}


	public void saveNewEventTemplate(String title, String description, ArrayList<Tag> tags) {
	EventTemplate eventTemplate = new EventTemplate(title, description, tags, null, Double.valueOf(0));
	model.saveEventTemplate(eventTemplate);
	}
	
	public ArrayList<EventTemplate> getEventTemplates() {
		return model.getEventTemplates();
	}
}
