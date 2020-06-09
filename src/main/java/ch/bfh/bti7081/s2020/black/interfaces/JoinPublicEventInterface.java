package ch.bfh.bti7081.s2020.black.interfaces;

import java.util.ArrayList;
import java.util.Set;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;

public interface JoinPublicEventInterface {
	
	public void joinPublicEvent(Set<Account> participants);

	public ArrayList<Event> getOpenPublicEvents();

	public void selectEvent(Event event);

	public ArrayList<Account> getFriends();

}
