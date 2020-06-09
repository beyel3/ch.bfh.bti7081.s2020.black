package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.*;
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
	
	// Get Event Templates
    public ArrayList<EventTemplate> getEventTemplateList() {
        ArrayList<EventTemplate> eventTemplateList = new ArrayList<EventTemplate>();
        try {
            ResultSet rs = persistence.executeQuery("SELECT * FROM tbl_eventTemplate");
            while (rs.next()) {
				ArrayList<Tag> tags = getTagsByTemplateID(rs.getInt("eventTemplateId"));
				EventTemplate eT = new EventTemplate(rs.getInt("eventTemplateId"), rs.getString("title"), rs.getString("description"), tags, rs.getDouble("rating"));
                eventTemplateList.add(eT);
            }
            return eventTemplateList;
        } catch (SQLException e) {
			e.printStackTrace();
            return null;
        }
    }

    // Get Events from specific Template
	public ArrayList<Event> getEventListByTemplate(EventTemplate eventTemplate) {

		ArrayList<Event> events = new ArrayList<Event>();
		try {
			ResultSet eventResult = persistence.executeQuery("SELECT * FROM tbl_event WHERE eventTemplateID = " + eventTemplate.getId());

			while (eventResult.next()) {
				ArrayList<Account> participants = new ArrayList<Account>();
				ResultSet participantsResult = persistence.executeQuery("SELECT a.accountID, a.first_name, a.last_name, a.email, a.accountType, a.level, a.patientInfo FROM tbl_participants AS p INNER JOIN tbl_account AS a ON p.accountID = a.accountID WHERE p.eventID = " + eventResult.getInt("eventID"));
				while (participantsResult.next()) {
					switch (AccountType.valueOf(participantsResult.getString(5))) {
						case PATIENT:
							Patient pat = new Patient(participantsResult.getInt(1),participantsResult.getString(2), participantsResult.getString(3), participantsResult.getString(4));
							pat.setPatientInfo(participantsResult.getString(7));
							participants.add(pat);
							break;
						case RELATIVE:
							Relative rel = new Relative(participantsResult.getInt(1),participantsResult.getString(2), participantsResult.getString(3), participantsResult.getString(4));
							participants.add(rel);
							break;
						default:
							break;
					}
				}
				Event event = new Event(eventResult.getInt("eventId"), eventTemplate, null,eventResult.getBoolean("isPublic"), eventResult.getInt("maxParticipants"), eventResult.getInt("rating"), Status.valueOf(eventResult.getString("state")), participants);
				ArrayList<Post> posts = getPostsByEventID(event);
				event.setPosts(posts);
				events.add(event);
			}
			return events;
		}
		catch(SQLException e){
			// query failed
			e.printStackTrace();
			return null;
		}
	}

	// Get Tags from from specific Template
	public ArrayList<Tag> getTagsByTemplateID(int id) {
		ArrayList<Tag> tags = new ArrayList<Tag>();
		try {
			ResultSet tagResult = persistence.executeQuery("SELECT t.tagID, t.tag_name FROM tbl_tagEventTemplateREL as tr INNER JOIN tbl_tag as t on tr.tagID = t.tagID WHERE tr.eventTemplateID =" + id);
			while (tagResult.next()) {
				Tag t = new Tag(tagResult.getInt(1), tagResult.getString(2));
				tags.add(t);
			}
			return tags;
		}
		catch(SQLException e){
			// query failed
			e.printStackTrace();
			return null;
		}
	}


	public ArrayList<Post> getPostsByEventID(Event event){
	
		ArrayList<Post> posts = new ArrayList<Post>();
		try {
			ResultSet postResult = persistence.executeQuery("SELECT a.accountID, a.first_name, a.last_name, a.email, a.accountType, a.level, a.patientInfo, p.content, p.creationTime FROM tbl_post AS p INNER JOIN tbl_account AS a ON p.accountID = a.accountID WHERE p.eventID = " + event.getId());

			while (postResult.next()) {
				switch (AccountType.valueOf(postResult.getString(5))) {
					case PATIENT:
						Patient pat = new Patient(postResult.getInt(1),postResult.getString(2), postResult.getString(3), postResult.getString(4));
						pat.setPatientInfo(postResult.getString(7));
						Post pp = new Post(postResult.getString(8), event, postResult.getTimestamp(9), pat);
						posts.add(pp);
					
						break;
					case RELATIVE:
						Relative rel = new Relative(postResult.getInt(1),postResult.getString(2), postResult.getString(3), postResult.getString(4));
						Post pr = new Post(postResult.getString(8), event, postResult.getTimestamp(9), rel);
						posts.add(pr);
						break;
					default:
						break;
				}
			}
			return posts;
		}
		catch(SQLException e){
			// query failed
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Account> getParticipantsByEventID(int id){
		ArrayList<Account> participants = new ArrayList<Account>();
		try {
			ResultSet participantsResult = persistence.executeQuery("SELECT a.accountID, a.first_name, a.last_name, a.email, a.accountType, a.level, a.patientInfo FROM tbl_participants AS p INNER JOIN tbl_account AS a ON p.accountID = a.accountID WHERE p.eventID = " + id);
			while (participantsResult.next()) {
				switch (AccountType.valueOf(participantsResult.getString(5))) {
					case PATIENT:
						Patient pat = new Patient(participantsResult.getInt(1), participantsResult.getString(2), participantsResult.getString(3), participantsResult.getString(4));
						pat.setPatientInfo(participantsResult.getString(7));
						participants.add(pat);
						break;
					case RELATIVE:
						Relative rel = new Relative(participantsResult.getInt(1), participantsResult.getString(2), participantsResult.getString(3), participantsResult.getString(4));

						participants.add(rel);
						break;
					default:
						break;
				}
			}
			return participants;
		}
		catch(SQLException e){
			// query failed
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Account> getFriends(Account acc){
		ArrayList<Account> friends = new ArrayList<Account>();
		try {
			ResultSet accResult = persistence.executeQuery("SELECT a.accountID, a.first_name, a.last_name, a.email, a.accountType, a.level, a.patientInfo FROM tbl_friendship AS f INNER JOIN tbl_account AS a ON f.accountID2 = a.accountID WHERE f.accountID1 = " + acc.getId());
			while (accResult.next()) {
				switch (AccountType.valueOf(accResult.getString(5))) {
					case PATIENT:
						Patient pat = new Patient(accResult.getInt(1), accResult.getString(2), accResult.getString(3), accResult.getString(4));
						pat.setPatientInfo(accResult.getString(7));
						friends.add(pat);
						break;
					case RELATIVE:
						Relative rel = new Relative(accResult.getInt(1), accResult.getString(2), accResult.getString(3), accResult.getString(4));
						friends.add(rel);
						break;
					default:
						break;
				}
			}
			return friends;
		}
		catch(SQLException e){
			// query failed
			e.printStackTrace();
			return null;
		}
	}
	
	
	public EventTemplate getEventTemplateByID(int eventTemplateID) {

		try {
			ArrayList<Tag> tags = new ArrayList<Tag>();

			ResultSet tagResult = persistence.executeQuery("SELECT t.tagID, t.tag_name FROM tbl_tagEventTemplateREL AS tr INNER JOIN tbl_tag AS t on tr.tagID = t.tagID WHERE tr.eventTemplateID =" + eventTemplateID);
			while (tagResult.next()) {
				Tag t = new Tag(tagResult.getInt(1), tagResult.getString(2));
				tags.add(t);
			}

			ResultSet templateResult = persistence.executeQuery("SELECT * FROM tbl_eventTemplate WHERE eventTemplateID = " + eventTemplateID);
			EventTemplate et = new EventTemplate(templateResult.getInt("eventTemplateId"), templateResult.getString("title"), templateResult.getString("description"), tags, templateResult.getDouble("rating"));

			return et;
		}
		catch(SQLException e) {
			// query failed
			System.err.println(e);
			return null;
		}
	}
}
