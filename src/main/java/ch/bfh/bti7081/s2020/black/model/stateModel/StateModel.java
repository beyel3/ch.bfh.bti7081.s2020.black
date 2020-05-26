package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
    public ArrayList<EventTemplate> getEventTemplateList() {
        ArrayList<EventTemplate> eventTemplateList = new ArrayList<EventTemplate>();
        try {
           
            ResultSet rs = persistence.executeQuery("SELECT * FROM tbl_eventTemplate");

            while (rs.next()) {
				ArrayList<Tag> tags = getTagsByTemplateID(rs.getInt("eventTemplateId"));
				EventTemplate eT = new EventTemplate(rs.getInt("eventTemplateId"), rs.getString("title"), rs.getString("description"), tags, null, rs.getDouble("rating"));
                ArrayList<Event> events = getEventListByTemplate(eT);
				eT.setEvents(events);
                eventTemplateList.add(eT);
            }
            return eventTemplateList;
        } catch (SQLException e) {
            // query failed
			e.printStackTrace();
            return null;
        }
    }

	public ArrayList<Event> getEventListByTemplate(EventTemplate eventTemplate) {

		ArrayList<Event> events = new ArrayList<Event>();
		try {
			ResultSet eventResult = persistence.executeQuery("SELECT * FROM tbl_event WHERE eventTemplateID = " + eventTemplate.getId());

			while (eventResult.next()) {
				ArrayList<Account> participants = new ArrayList<Account>();
				ResultSet participantsResult = persistence.executeQuery("SELECT a.first_name, a.last_name, a.email, a.accountType, a.level, a.patientInfo FROM tbl_participants AS p INNER JOIN tbl_account AS a ON p.accountID = a.accountID WHERE p.eventID = " + eventResult.getInt("eventID"));
				while (participantsResult.next()) {
					switch (AccountType.valueOf(participantsResult.getString(4))) {
						case PATIENT:
							Patient pat = new Patient(participantsResult.getString(1), participantsResult.getString(2), participantsResult.getString(3),participantsResult.getString(4));
							pat.setPatientInfo(participantsResult.getString(6));
							participants.add(pat);
							break;
						case RELATIVE:
							Relative rel = new Relative(participantsResult.getString(1), participantsResult.getString(2), participantsResult.getString(3),participantsResult.getString(4));
							rel.setLvl(participantsResult.getInt(5));
							participants.add(rel);
							break;
						default:
							break;
					}
				}
				Event event = new Event(eventResult.getInt("eventId"), eventTemplate, eventResult.getString("info"), null,eventResult.getBoolean("isPublic"), eventResult.getInt("maxParticipants"), eventResult.getInt("rating"), Status.valueOf(eventResult.getString("state")), eventResult.getInt("imageID"), participants);
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

	public Tag saveTag(Tag t) {

		try {
			persistence.executeUpdate("INSERT INTO tbl_tag VALUES (NULL, '"+t.getTagName()+"')");
			ResultSet id = persistence.executeQuery("SELECT LAST_INSERT_ROWID()");
			t.setId(id.getInt(1));

			return t;
		}
		catch(SQLException e){
			// query failed
			e.printStackTrace();
			return null;
		}
	}

	public Tag getTagById(int id) {
		try {
			ResultSet tr = persistence.executeQuery("SELECT tagID, tag_name FROM tbl_tag WHERE tagID = "+id);
			Tag t = new Tag(tr.getInt(1), tr.getString(2));
			return t;
		}
		catch(SQLException e){
			// query failed
			e.printStackTrace();
			return null;
		}
	}

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

	public ArrayList<Tag> getTagList() {
		ArrayList<Tag> tags = new ArrayList<Tag>();

		try {
			ResultSet tagResult = persistence.executeQuery("SELECT * FROM tbl_tag");
			while (tagResult.next()) {
				Tag t = new Tag(tagResult.getInt(1), tagResult.getString(2));
				tags.add(t);
			}
			return tags;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Patient> getPatientList() {
		/*
		ArrayList<Patient> list = new ArrayList<>();
		try {

			ResultSet rs = persistence.executeQuery("SELECT * FROM tbl_accounts WHERE accountType like 'patient'");
			while (rs.next()) {
				Patient us = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4));
				list.add(us);
			}
			return list;
		}
		catch(SQLException e){
			// query failed
			e.printStackTrace();
			return null;
		}

		 */
		return null;
	}

	public void savePost(Post post, Account acc, Event event){
		persistence.executeUpdate("INSERT INTO tbl_post VALUES ("+acc.getId()+", "+event.getId()+", "+post.getMessage()+", "+post.getDate()+")");
	}

	public ArrayList<Post> getPostsByEventID(Event event){
		ArrayList<Post> posts = new ArrayList<Post>();
		try {
			ResultSet postResult = persistence.executeQuery("SELECT a.first_name, a.last_name, a.email, a.accountType, a.level, a.patientInfo, p.content, p.creationTime FROM tbl_post AS p INNER JOIN tbl_account AS a ON p.accountID = a.accountID WHERE p.eventID = " + event.getId());

			while (postResult.next()) {
				switch (AccountType.valueOf(postResult.getString(4))) {
					case PATIENT:
						Patient pat = new Patient(postResult.getString(1), postResult.getString(2), postResult.getString(3),null);
						pat.setPatientInfo(postResult.getString(6));
						Post pp = new Post(postResult.getString(7), event, postResult.getDate(8), pat);
						posts.add(pp);
						break;
					case RELATIVE:
						Relative rel = new Relative(postResult.getString(1), postResult.getString(2), postResult.getString(3),null);
						rel.setLvl(postResult.getInt(5));
						Post pr = new Post(postResult.getString(7), event, postResult.getDate(8), rel);
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

	public ArrayList<Account> getFirends(Account acc){
		ArrayList<Account> friends = new ArrayList<Account>();
		try {
			ResultSet accResult = persistence.executeQuery("a.first_name, a.last_name, a.email, a.accountType, a.level, a.patientInfo FROM tbl_friendship AS f INNER JOIN tbl_account AS a ON f.accountID2 = a.accountID WHERE f.accountID1 = " + acc.getId());
			while (accResult.next()) {
				switch (AccountType.valueOf(accResult.getString(4))) {
					case PATIENT:
						Patient pat = new Patient(accResult.getString(1), accResult.getString(2), accResult.getString(3), accResult.getString(4));
						pat.setPatientInfo(accResult.getString(6));
						friends.add(pat);
						break;
					case RELATIVE:
						Relative rel = new Relative(accResult.getString(1), accResult.getString(2), accResult.getString(3), accResult.getString(4));
						rel.setLvl(accResult.getInt(5));
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

	//	public Post(String message, Event event, Date date, Account account) {
//	public EventTemplate saveEventTemplate(EventTemplate et) throws SQLException{
//		ArrayList<Tag> tags = et.getTags();
//
//		try {
//
//			Statement statement = this.connection.createStatement();
//			statement.setQueryTimeout(30);  // set timeout to 30 sec.
//			statement.executeUpdate("INSERT INTO tbl_eventTemplate VALUES (NULL, '"+et.getTitle()+"', '"+et.getDescription()+"', '"+et.getAvgRating()+"')");
//			ResultSet id = statement.executeQuery("SELECT LAST_INSERT_ROWID()");
//			et.setId(id.getInt(1));
//
//			for (Tag t:tags) {
//				statement.executeUpdate("INSERT INTO tbl_tagEventTemplateREL(tagID,eventTemplateID) SELECT "+t.getId()+", '"+et.getId()+"' WHERE NOT EXISTS(SELECT 1 FROM tbl_tagEventTemplateREL WHERE tagID = "+t.getId()+" AND eventTemplateID = "+et.getId()+");");
//			}
//
//			//return eventTemplate with updated id
//			return et;
//		}
//		catch(SQLException e) {
//			// query failed
//			System.err.println(e);
//			return null;
//		}
//	}
//
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
//
//    public EventTemplate getEventTemplateById(int id) throws SQLException {
//        ArrayList<Event> events = getEventsByTemplateId(id);
//        ArrayList<Tag> tags = getTagsByTemplateID(id);
//        try {
//            Statement statement = this.connection.createStatement();
//            statement.setQueryTimeout(30);  // set timeout to 30 sec.
//
//            ResultSet templateResult = statement.executeQuery("SELECT * FROM tbl_eventTemplate WHERE eventTemplateID = "+id);
//            EventTemplate eT = new EventTemplate(templateResult.getInt("eventTemplateId"), templateResult.getString("title"), templateResult.getString("description"), tags, events, templateResult.getDouble("rating"));
//
//            return eT;
//        }
//
//        catch(SQLException e){
//            // query failed
//            System.err.println(e);
//            return null;
//        }
//    }
//
//    public ArrayList<EventTemplate> getEventTemplateList() throws SQLException {
//        ArrayList<EventTemplate> eventTemplateList = new ArrayList<EventTemplate>();
//        try {
//            Statement statement = this.connection.createStatement();
//            statement.setQueryTimeout(30);  // set timeout to 30 sec.
//            ResultSet rs = statement.executeQuery("SELECT * FROM tbl_eventTemplate");
//
//            while (rs.next()) {
//                ArrayList<Event> events = getEventsByTemplateId(rs.getInt("eventTemplateId"));
//                ArrayList<Tag> tags = getTagsByTemplateID(rs.getInt("eventTemplateId"));
//                EventTemplate eT = new EventTemplate(rs.getInt("eventTemplateId"), rs.getString("title"), rs.getString("description"), tags, events, rs.getDouble("rating"));
//                eventTemplateList.add(eT);
//            }
//            return eventTemplateList;
//        } catch (SQLException e) {
//            // query failed
//            System.err.println(e);
//            return null;
//        }
//    }

}
