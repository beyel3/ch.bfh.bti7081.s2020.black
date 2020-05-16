package ch.bfh.bti7081.s2020.black.model.eventStateModel;

import java.sql.SQLException;
import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;

public class ChooseTemplate extends EventStateModel {

	
	public void saveEventTemplate(EventTemplate eventTemplate) {

		try {

			persistence.saveEventTemplate(eventTemplate);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public ArrayList<EventTemplate> getEventTemplates() {
		try {
			
			return persistence.getEventTemplateList();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


}
