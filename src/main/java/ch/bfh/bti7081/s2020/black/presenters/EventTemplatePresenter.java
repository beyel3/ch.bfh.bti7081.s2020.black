package ch.bfh.bti7081.s2020.black.presenters;

import java.util.List;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.HardCoded;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;

import static ch.bfh.bti7081.s2020.black.views.MainView.hardCoded;

public class EventTemplatePresenter {
	
	private Persistence persistence;
	
	public EventTemplatePresenter() {
		
		try {
			persistence = new Persistence();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public List<EventTemplate> getEventTemplates() {
		
		
//		return persistence.getEventTemplateList();
		return hardCoded.getEventTemplates();
	}
	
	

}
