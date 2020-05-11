package ch.bfh.bti7081.s2020.black.presenters;

import java.util.List;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.HardCoded;

public class EventTemplatePresenter {
	
	private HardCoded hardCoded = new HardCoded();
	
	public EventTemplatePresenter() {
		
		
		
	}

	public List<EventTemplate> getEventTemplates() {
		return hardCoded.getEventTemplates();
	}
	
	

}
