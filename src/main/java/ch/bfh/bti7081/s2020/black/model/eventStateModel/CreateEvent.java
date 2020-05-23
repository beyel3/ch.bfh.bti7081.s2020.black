package ch.bfh.bti7081.s2020.black.model.eventStateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.*;

public class CreateEvent extends EventStateModel {

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
<<<<<<< HEAD
=======

	public void addParticipant(Account user, Event event){
		persistence.executeUpdate("INSERT INTO tbl_participants VALUES ("+user.getId()+", "+event.getId()+")");
	}
	
	public void addEventToTemplate(Event event) {
	
		
	}
>>>>>>> Dev
	
	
	public EventTemplate getEventTemplateByID(int eventTemplateID) {

		try {
			ArrayList<Tag> tags = new ArrayList<Tag>();

			ResultSet tagResult = persistence.executeQuery("SELECT t.tagID, t.tag_name FROM tbl_tagEventTemplateREL as tr INNER JOIN tbl_tag as t on tr.tagID = t.tagID WHERE tr.eventTemplateID =" + eventTemplateID);
			while (tagResult.next()) {
				Tag t = new Tag(tagResult.getInt(1), tagResult.getString(2));
				tags.add(t);
			}

			//return persistence.getEventTemplateById(eventTemplateID);
			ResultSet templateResult = persistence.executeQuery("SELECT * FROM tbl_eventTemplate WHERE eventTemplateID = " + eventTemplateID);
			EventTemplate et = new EventTemplate(templateResult.getInt("eventTemplateId"), templateResult.getString("title"), templateResult.getString("description"), tags, null, templateResult.getDouble("rating"));

			ArrayList<Event> events = new ArrayList<Event>();
			ResultSet eventResult = persistence.executeQuery("SELECT * FROM tbl_event WHERE eventTemplateID = " + eventTemplateID);

			while (eventResult.next()) {
				ArrayList<Account> participants = new ArrayList<Account>();
				ResultSet participantsResult = persistence.executeQuery("SELECT a.first_name, a.last_name, a.email, a.accountType, a.level, a.patientInfo FROM tbl_participants AS p INNER JOIN tbl_account AS a ON p.accountID = a.accountID WHERE p.eventID = " + eventResult.getInt("eventID"));
				while (participantsResult.next()) {
					switch (AccountType.valueOf(participantsResult.getString(4))) {
						case PATIENT:
							Patient pat = new Patient(participantsResult.getString(1), participantsResult.getString(2), participantsResult.getString(3));
							pat.setPatientInfo(participantsResult.getString(6));
							participants.add(pat);
							break;
						case RELATIVE:
							Relative rel = new Relative(participantsResult.getString(1), participantsResult.getString(2), participantsResult.getString(3));
							rel.setLvl(participantsResult.getInt(5));
							participants.add(rel);
							break;
						default:
							break;
					}
				}
				Event event = new Event(eventResult.getInt("eventId"), et, eventResult.getString("info"), eventResult.getBoolean("isPublic"), eventResult.getInt("maxParticipants"), eventResult.getInt("rating"), Status.valueOf(eventResult.getString("state")), eventResult.getInt("imageID"), participants);
				events.add(event);
			}
			et.setEvents(events);
			return et;
		}
		catch(SQLException e) {
			// query failed
			System.err.println(e);
			return null;
		}
	}
<<<<<<< Updated upstream
=======

	public void saveEvent(Event event) {

		
	}
>>>>>>> Stashed changes
}

