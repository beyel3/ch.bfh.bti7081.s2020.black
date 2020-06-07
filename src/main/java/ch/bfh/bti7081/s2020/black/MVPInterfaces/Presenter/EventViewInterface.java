package ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter;

import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.Post;

public interface EventViewInterface {
	
	public void buttonClick(EventAction action, Event selected);
	public ArrayList<Event> getMyEvents();
	public Event getSelectedEvent();
	public ArrayList<Post> getPosts(Event event);
	public void submitPost(String post);
	    public enum EventAction{
        OPENCHAT,
        CLOSECHAT,
        DETAILS,
        MARKDONE
    }
		

}
