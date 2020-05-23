package ch.bfh.bti7081.s2020.black.presenters;

import java.util.ArrayList;
import java.util.Set;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.stateModel.CreateEvent;
import ch.bfh.bti7081.s2020.black.views.CreateEventViewImplementaion;

public class CreateEventPresenter extends Presenter {

	private CreateEvent createEventState;
	private EventTemplate eventTemplate;
	
	public CreateEventPresenter(SuperPresenter superPresenter, EventTemplate eventTemplate) {
		super(superPresenter);
		
		this.createEventState = new CreateEvent();
		superPresenter.setState(createEventState);
		
		this.eventTemplate = eventTemplate;
		this.currentView = new CreateEventViewImplementaion(this);
		
	}



	public void saveEvent(Boolean isPublic, int maxParticipants, Set<Account> set) {
		
		ArrayList<Account> accounts = new ArrayList<>();
		
		for(Account a : set) {
			accounts.add(a);
		}
		
		Event event = new Event(eventTemplate, isPublic, maxParticipants, accounts);
		createEventState.saveEvent(event);
		
	}


}
