package ch.bfh.bti7081.s2020.black.model.eventStateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ch.bfh.bti7081.s2020.black.model.Coreuser;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;

public class CreateEvent extends EventStateModel {
	
	public CreateEvent() {

	}

	
	public void addEventToTemplate(Event event) {
	
		
	}
	
	
	public EventTemplate getEventTemplateByID(int eventTemplateID) {
		
		try {
			return persistence.getEventTemplateById(eventTemplateID);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

