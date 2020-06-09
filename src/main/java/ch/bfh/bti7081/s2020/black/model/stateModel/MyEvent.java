package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.Patient;
import ch.bfh.bti7081.s2020.black.model.Post;
import ch.bfh.bti7081.s2020.black.model.Status;

public class MyEvent extends StateModel {

	public ArrayList<Event> getEventListByAccount(Account loggedInAccount) {

		ArrayList<Event> events = new ArrayList<Event>();

		try {
			ResultSet eventResult = persistence.executeQuery("SELECT e.eventID, e.eventTemplateID, e.info, e.isPublic, e.maxParticipants, e.rating, e.state, e.imageID FROM tbl_participants AS p INNER JOIN tbl_event AS e ON p.eventID = e.eventID WHERE p.accountID = "+loggedInAccount.getId());

			while (eventResult.next()) {
				ArrayList<Account> participants = getParticipantsByEventID(eventResult.getInt(1));
				Event event = new Event(eventResult.getInt(1), getEventTemplateByID(eventResult.getInt(2)), null,eventResult.getBoolean(4), eventResult.getInt(5), eventResult.getInt(6), Status.valueOf(eventResult.getString(7)), participants);
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
	
	public ArrayList<Event> getOpenEventListByAccount(Account account) {

		ArrayList<Event> events = new ArrayList<Event>();

		try {
			ResultSet eventResult = persistence.executeQuery("SELECT e.eventID, e.eventTemplateID, e.info, e.isPublic, e.maxParticipants, e.rating, e.state, e.imageID FROM tbl_participants AS p INNER JOIN tbl_event AS e ON p.eventID = e.eventID WHERE e.state = 'open' AND p.accountID = "+account.getId());

			while (eventResult.next()) {
				ArrayList<Account> participants = getParticipantsByEventID(eventResult.getInt(1));
				Event event = new Event(eventResult.getInt(1), getEventTemplateByID(eventResult.getInt(2)), null,eventResult.getBoolean(4), eventResult.getInt(5), eventResult.getInt(6), Status.valueOf(eventResult.getString(7)), participants);
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
	
	public ArrayList<Event> getDoneEventListByAccount(Account loggedInAccount) {

		ArrayList<Event> events = new ArrayList<Event>();

		try {
			ResultSet eventResult = persistence.executeQuery("SELECT e.eventID, e.eventTemplateID, e.info, e.isPublic, e.maxParticipants, e.rating, e.state, e.imageID FROM tbl_participants AS p INNER JOIN tbl_event AS e ON p.eventID = e.eventID WHERE e.state = 'done' AND p.accountID = "+loggedInAccount.getId());

			while (eventResult.next()) {
				ArrayList<Account> participants = getParticipantsByEventID(eventResult.getInt(1));
				Event event = new Event(eventResult.getInt(1), getEventTemplateByID(eventResult.getInt(2)), null,eventResult.getBoolean(4), eventResult.getInt(5), eventResult.getInt(6), Status.valueOf(eventResult.getString(7)), participants);
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
	
	public void savePost(String message, Account acc, Event event){

		Post post = new Post(message, event, acc);
		persistence.executeUpdate("INSERT INTO tbl_post VALUES (" + acc.getId() + ", " + event.getId() + ",'" + post.getMessage() + "', '" + post.getDate() + "')");
	}

	
	public byte [] loadPicture(Event event) throws SQLException {
		
		ResultSet rs = persistence.executeQuery("SELECT image FROM tbl_image WHERE eventID =" + event.getId());
		
		InputStream test = rs.getBinaryStream(1);
		byte[] blobAsBytes = null;
		try {
			blobAsBytes = IOUtils.toByteArray(test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return blobAsBytes;
	}
	
	public ArrayList<Patient> getPatientListWithNoRel(Account acc) {

		ArrayList<Patient> list = new ArrayList<>();
		try {

			ResultSet rs = persistence.executeQuery("SELECT accountID, first_name, last_name, email, patientInfo FROM tbl_account WHERE accountID NOT IN(SELECT accountID2 FROM tbl_friendship WHERE accountID1 = "+acc.getId()+") AND accountType LIKE 'patient' AND accountID != " + acc.getId());
			while (rs.next()) {
				Patient us = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				us.setPatientInfo(rs.getString(5));
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
	
	public void addFriend(Patient patient, Account acc){		
		persistence.executeUpdate("INSERT INTO tbl_friendship VALUES ("+acc.getId()+", "+patient.getId()+")");
	}

}
