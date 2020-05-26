package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;

public class CreateEvent extends StateModel {

	public Event saveEvent(Event event) {
		// Das setzt voraus, dass das Event objekt bereits die EventTemplateID seines EventTemplates kennt
		try {
			persistence.executeUpdate("INSERT INTO tbl_event VALUES (NULL,'" + event.getInfo() + "'," + event.isPublic() + "," + event.getRating() + "," + event.getStatus().toString() + "," + event.getMaxParticipants() + "," + event.getEventTemplate().getId() + "," + event.getPictureID() + ")");
			ResultSet rs = persistence.executeQuery("SELECT LAST_INSERT_ROWID()");
			event.setId(rs.getInt(1));

			for (Account a:event.getParticipants()){
				persistence.executeUpdate("INSERT INTO tbl_participants VALUES ("+a.getId()+", "+event.getId()+")");
			}

			return event;
		}
		catch(SQLException e) {
			// query failed
			System.err.println(e);
			return null;
		}
	}
	
	


	public void addParticipant(Account user, Event event){
		persistence.executeUpdate("INSERT INTO tbl_participants VALUES ("+user.getId()+", "+event.getId()+")");
	}
	
	public void addEventToTemplate(Event event) {
	
		
	}
	
}

