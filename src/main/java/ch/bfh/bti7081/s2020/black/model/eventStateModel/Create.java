package ch.bfh.bti7081.s2020.black.model.eventStateModel;

import java.sql.SQLException;
import java.util.List;

import ch.bfh.bti7081.s2020.black.model.Coreuser;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;

public class Create extends StateModel {
	
	public Create() {

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

	public void saveEvent(Event event) {
		List<Coreuser> participants = event.getParticipants();

		persistence.executeQuery("INSERT INTO tbl_event VALUES (NULL,'" + event.getInfo() + "'," + event.isPublic() + "," + event.getRating() + "," + event.getStatus().toString() + "," + event.getMaxParticipants() + "," + event.getEventTemplate().getId() + "," + event.getPictureID() + ")");
		//ResultSet rs = persistence.executeQuery("SELECT LAST_INSERT_ROWID()");
		//event.setId(rs.getInt(1));

		// Wo und wann schreiben wir die Participants in die DB?
		//		for (Coreuser c:participants){
		//
		//		}

	}
}

