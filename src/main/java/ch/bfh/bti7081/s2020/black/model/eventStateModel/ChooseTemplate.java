package ch.bfh.bti7081.s2020.black.model.eventStateModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;

public class ChooseTemplate extends EventStateModel {

	private Random random = new Random();

	public ArrayList<EventTemplate> getEventTemplates() {

		try {
			
			ArrayList<EventTemplate> list = persistence.getEventTemplateList();
			
//			
//			for(EventTemplate t : list) {
//				
//				System.out.println(t);
//			}
			
			return list;
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Tag> getTagsList() {

		try {
			return persistence.getTagList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public EventTemplate saveNewEventTemplate(String title, String description, ArrayList<Tag> tags) {

		EventTemplate newEventTemplate = new EventTemplate(title, description, tags);
			
		
		try {
			newEventTemplate = persistence.saveEventTemplate(newEventTemplate);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return newEventTemplate;
	}
}
