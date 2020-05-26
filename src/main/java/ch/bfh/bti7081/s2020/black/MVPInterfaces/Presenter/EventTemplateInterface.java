package ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter;

import java.util.ArrayList;
import java.util.Set;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;

public interface EventTemplateInterface {
	
	public ArrayList<EventTemplate> getEventTemplates();
	public ArrayList<Tag> getTags();
	public void buttonClick(EventTemplate eventTemplate);
	public void createTempalte();
	public void submit(String title, String description, Set<Tag> tags);
		
	
}
