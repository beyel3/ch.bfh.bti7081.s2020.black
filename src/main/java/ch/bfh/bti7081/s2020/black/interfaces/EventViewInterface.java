package ch.bfh.bti7081.s2020.black.interfaces;

import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.Patient;
import ch.bfh.bti7081.s2020.black.model.Post;

public interface EventViewInterface {

	public void buttonClick(EventAction action, Event selected);

	public enum EventAction {
		OPENCHAT, CLOSECHAT, ADDFRIEND, MARKDONE
	}

	public void submitPost(String post);

	public void enablePatientInfoView(Account acc);

	public void addFriend(Patient patient);

	public int getLoggedInAccountID();

	public byte[] getPicture(Event singleEvent);

	public ArrayList<Event> getMyEvents();

	public ArrayList<Event> getPatientEvents(Account acc);

	public ArrayList<Event> getMyOpenEvents();

	public ArrayList<Event> getMyDoneEvents();

	public ArrayList<Account> getMyFriends();

	public ArrayList<Patient> getAccounts();

	public Event getSelectedEvent();

	public ArrayList<Post> getPosts(Event event);

}
