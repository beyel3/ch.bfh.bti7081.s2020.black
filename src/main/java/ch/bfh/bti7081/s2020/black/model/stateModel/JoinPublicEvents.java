package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.Post;
import ch.bfh.bti7081.s2020.black.model.Status;
//import ch.bfh.bti7081.s2020.black.model.HardCoded;

public class JoinPublicEvents extends StateModel {

	public ArrayList<Event> getOpenPublicEvents() {

		ArrayList<Event> events = new ArrayList<Event>();

		try {
			ResultSet eventResult = persistence.executeQuery("SELECT e.eventID, e.info, e.isPublic, e.maxParticipants, e.rating, e.state, e.imageID FROM tbl_event AS e WHERE e.state = '"+Status.open.toString()+"'");

			while (eventResult.next()) {
				ArrayList<Account> participants = getParticipantsByEventID(eventResult.getInt(1));
				Event event = new Event(eventResult.getInt(1), getEventTemplateByID(eventResult.getInt(1)), eventResult.getString(2), null,eventResult.getBoolean(3), eventResult.getInt(4), eventResult.getInt(5), Status.valueOf(eventResult.getString(6)), null, participants);
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
		//return  new HardCoded().getEvent();
	}

	public void joinPublicEvent(ArrayList<Account> users, Event event){
		
		for(Account a : users) {
		persistence.executeUpdate("INSERT INTO tbl_participants VALUES ("+a.getId()+", "+event.getId()+")");
		}
	}

}
