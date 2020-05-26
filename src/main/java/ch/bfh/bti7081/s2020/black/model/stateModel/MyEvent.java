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
		
//			ArrayList<Event> events = new ArrayList<Event>();
//			
//			try {
//				ResultSet eventResult = persistence.executeQuery("SELECT * FROM tbl_event natural join tbl_participants natural join tbl_accounts WHERE accountID = " + loggedInAccount.getId());
//
//				while (eventResult.next()) {
//					ArrayList<Account> participants = new ArrayList<Account>();
//					ResultSet participantsResult = persistence.executeQuery("SELECT a.first_name, a.last_name, a.email, a.accountType, a.level, a.patientInfo FROM tbl_participants AS p INNER JOIN tbl_account AS a ON p.accountID = a.accountID WHERE p.eventID = " + eventResult.getInt("eventID"));
//					while (participantsResult.next()) {
//						switch (AccountType.valueOf(participantsResult.getString(4))) {
//							case PATIENT:
//								Patient pat = new Patient(participantsResult.getString(1), participantsResult.getString(2), participantsResult.getString(3),participantsResult.getString(4));
//								pat.setPatientInfo(participantsResult.getString(6));
//								participants.add(pat);
//								break;
//							case RELATIVE:
//								Relative rel = new Relative(participantsResult.getString(1), participantsResult.getString(2), participantsResult.getString(3),participantsResult.getString(4));
//								rel.setLvl(participantsResult.getInt(5));
//								participants.add(rel);
//								break;
//							default:
//								break;
//						}
//					}
//					Event event = new Event(eventResult.getInt("eventId"), eventTemplate, eventResult.getString("info"), null,eventResult.getBoolean("isPublic"), eventResult.getInt("maxParticipants"), eventResult.getInt("rating"), Status.valueOf(eventResult.getString("state")), eventResult.getInt("imageID"), participants);
//					ArrayList<Post> posts = getPostsByEventID(event);
//					event.setPosts(posts);
//					events.add(event);
//				}
//				return events;
//			}
//			catch(SQLException e){
//				// query failed
//				e.printStackTrace();
//				return null;
//			}
		
		return  new HardCoded().getEvent();
	}
	
	

}
