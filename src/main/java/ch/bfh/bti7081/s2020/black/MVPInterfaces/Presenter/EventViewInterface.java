package ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter;

import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Patient;
import ch.bfh.bti7081.s2020.black.model.Post;

public interface EventViewInterface {
	
	public void buttonClick(EventAction action, Event selected);
	public ArrayList<Event> getMyEvents();
	public ArrayList<Event> getPatientEvents(Account acc);
	public ArrayList<Event> getMyOpenEvents();
	public ArrayList<Event> getMyDoneEvents();
	public ArrayList<Account> getMyFriends();
	public ArrayList<Patient> getAccounts();
	public void enablePatientInfoView(Account acc);
	public void addFriend(Patient patient);
	public Event getSelectedEvent();
	public ArrayList<Post> getPosts(Event event);
	public void submitPost(String post);
	    public enum EventAction{
        OPENCHAT,
        CLOSECHAT,
        ADDFRIEND,
        MARKDONE
    }
	public int getLoggedInAccountID();
	public byte[] getPicture(Event singleEvent);
		
}
