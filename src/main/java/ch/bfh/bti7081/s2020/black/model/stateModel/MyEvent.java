package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.AccountType;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.HardCoded;
import ch.bfh.bti7081.s2020.black.model.Patient;
import ch.bfh.bti7081.s2020.black.model.Post;
import ch.bfh.bti7081.s2020.black.model.Relative;
import ch.bfh.bti7081.s2020.black.model.Status;

public class MyEvent extends StateModel {

	public ArrayList<Event> getEventListByAccount(Account loggedInAccount) {

		ArrayList<Event> events = new ArrayList<Event>();
//		System.out.println(loggedInAccount.getId());
		try {
			ResultSet eventResult = persistence.executeQuery("SELECT e.eventID, e.info, e.isPublic, e.maxParticipants, e.rating, e.state, e.imageID FROM tbl_participants AS p INNER JOIN tbl_event AS e ON p.eventID = e.eventID WHERE p.accountID = "+loggedInAccount.getId());

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
	
	public void savePost(String message, Account acc, Event event){

		Post post = new Post(message, event, acc);

		persistence.executeUpdate("INSERT INTO tbl_post VALUES ("+acc.getId()+", "+event.getId()+", "+post.getMessage()+", "+post.getDate()+")");
	}

	public byte [] loadPicture(Event event) {
		return null;
	}
	
	public ArrayList<Patient> getPatientListWithNoRel(Account acc) {

		ArrayList<Patient> list = new ArrayList<>();
		try {

			ResultSet rs = persistence.executeQuery("SELECT a.first_name, a.last_name, a.email, a.patientInfo FROM tbl_friendship AS f INNER JOIN tbl_account AS a ON f.accountID2 = a.accountID WHERE f.accountID1 != " + acc.getId());
			while (rs.next()) {
				Patient us = new Patient(rs.getString(1), rs.getString(2), rs.getString(3));
				us.setPatientInfo(rs.getString(4));
				list.add(us);
			}
			return list;
		}
		catch(SQLException e){
			// query failed
			e.printStackTrace();
			return null;
		}
	}

}
