package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.Post;
import ch.bfh.bti7081.s2020.black.model.Status;

public class JoinPublicEvents extends StateModel {

	public ArrayList<Event> getOpenPublicEvents(Account acc) {
		ArrayList<Event> events = new ArrayList<Event>();

		try {
			ResultSet eventResult = persistence.executeQuery("SELECT e.eventID, e.eventTemplateID, e.info, e.isPublic, e.maxParticipants, e.rating, e.state, e.imageID FROM tbl_event AS e WHERE e.state = '"+Status.open.toString()+"' AND e.isPublic = 1");

			while (eventResult.next()) {
				ArrayList<Account> participants = getParticipantsByEventID(eventResult.getInt(1));
				if (participants.contains(acc)) {
					continue;
				}
				else {
					Event event = new Event(eventResult.getInt(1), getEventTemplateByID(eventResult.getInt(2)), null, eventResult.getBoolean(4), eventResult.getInt(5), eventResult.getInt(6), Status.valueOf(eventResult.getString(7)), participants);
					ArrayList<Post> posts = getPostsByEventID(event);
					event.setPosts(posts);
					events.add(event);
				}
			}
			return events;
		}
		catch(SQLException e){
			// query failed
			e.printStackTrace();
			return null;
		}
	}

	public void joinPublicEvent(ArrayList<Account> users, Event event){
		for(Account a : users) {
		persistence.executeUpdate("INSERT INTO tbl_participants VALUES ("+a.getId()+", "+event.getId()+")");
		}
	}

}
