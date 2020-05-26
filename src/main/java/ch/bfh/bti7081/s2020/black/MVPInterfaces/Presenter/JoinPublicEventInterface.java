package ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter;

import java.util.ArrayList;
import java.util.Set;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;

public interface JoinPublicEventInterface {
	
	public ArrayList<Event> getOpenPublicEvents();
	public void selectEvent(Event event);
	public void joinPublicEvent(Set<Account> participants);
	public ArrayList<Account> getFriends();

}
