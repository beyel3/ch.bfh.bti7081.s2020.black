package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.AccountType;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Patient;
import ch.bfh.bti7081.s2020.black.model.Relative;
import ch.bfh.bti7081.s2020.black.model.Status;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;
import ch.bfh.bti7081.s2020.black.presenters.SuperPresenter;

public abstract class StateModel {
	
	protected Persistence persistence;
	protected SuperPresenter presenter;
	
	
	public StateModel() {
		
		try {
			persistence = new Persistence();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
    public ArrayList<EventTemplate> getEventTemplateList() {
        ArrayList<EventTemplate> eventTemplateList = new ArrayList<EventTemplate>();
        try {
           
            ResultSet rs = persistence.executeQuery("SELECT * FROM tbl_eventTemplate");

            while (rs.next()) {
//                ArrayList<Event> events = getEventListByTemplateId(rs.getInt("eventTemplateId"));
//                ArrayList<Tag> tags = getTagsByTemplateID(rs.getInt("eventTemplateId"));
//                EventTemplate eT = new EventTemplate(rs.getInt("eventTemplateId"), rs.getString("title"), rs.getString("description"), tags, events, rs.getDouble("rating"));
//                eventTemplateList.add(eT);
            }
            return eventTemplateList;
        } catch (SQLException e) {
            // query failed
            System.err.println(e);
            return null;
        }
    }
	
	
//	public EventTemplate getEventTemplateByID(int eventTemplateID) {
//
//		try {
//			ArrayList<Tag> tags = new ArrayList<Tag>();
//
//			ResultSet tagResult = persistence.executeQuery("SELECT t.tagID, t.tag_name FROM tbl_tagEventTemplateREL as tr INNER JOIN tbl_tag as t on tr.tagID = t.tagID WHERE tr.eventTemplateID =" + eventTemplateID);
//			while (tagResult.next()) {
//				Tag t = new Tag(tagResult.getInt(1), tagResult.getString(2));
//				tags.add(t);
//			}
//
//			//return persistence.getEventTemplateById(eventTemplateID);
//			ResultSet templateResult = persistence.executeQuery("SELECT * FROM tbl_eventTemplate WHERE eventTemplateID = " + eventTemplateID);
//			EventTemplate et = new EventTemplate(templateResult.getInt("eventTemplateId"), templateResult.getString("title"), templateResult.getString("description"), tags, null, templateResult.getDouble("rating"));
//
//			
//			et.setEvents(getEventListByTemplateId(eventTemplateID));
//			return et;
//		}
//		catch(SQLException e) {
//			// query failed
//			System.err.println(e);
//			return null;
//		}
//	}
	
	public ArrayList<Event> getEventListByTemplate(EventTemplate eventTemplate) throws SQLException{
		
		
		ArrayList<Event> events = new ArrayList<Event>();
		ResultSet eventResult = persistence.executeQuery("SELECT * FROM tbl_event WHERE eventTemplateID = " + eventTemplate.getId());

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
			//Event event = new Event(eventResult.getInt("eventId"), eventTemplate, eventResult.getString("info"), eventResult.getBoolean("isPublic"), eventResult.getInt("maxParticipants"), eventResult.getInt("rating"), Status.valueOf(eventResult.getString("state")), eventResult.getInt("imageID"), participants);
			//events.add(event);
		}
		return events;
	}
	
    public ArrayList<Tag> getTagList() {
        ArrayList<Tag> tags = new ArrayList<Tag>();
 
   
            ResultSet tagResult = persistence.executeQuery("SELECT * FROM tbl_tag");
            
            try {
				while (tagResult.next()) {
				    Tag t = new Tag(tagResult.getInt(1), tagResult.getString(2));
				    tags.add(t);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return tags;

    }
}
