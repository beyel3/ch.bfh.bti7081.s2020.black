package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;

public class JoinPublicEvents extends StateModel {

	public ArrayList<Event> getOpenPublicEvents() {
		
		
		return null;
	}
	
	public void joinPublicEvent(ArrayList<Account> users, Event event){
		
		for(Account a : users) {
		persistence.executeUpdate("INSERT INTO tbl_participants VALUES ("+a.getId()+", "+event.getId()+")");
		}
	}

}
