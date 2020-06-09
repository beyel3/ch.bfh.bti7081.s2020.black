package ch.bfh.bti7081.s2020.black.presenters;

import java.util.ArrayList;
import java.util.Set;

import ch.bfh.bti7081.s2020.black.interfaces.CreateEventInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.stateModel.CreateEvent;
import ch.bfh.bti7081.s2020.black.views.CreateEventViewImplementaion;

public class CreateEventPresenter extends Presenter implements CreateEventInterface {

	private CreateEvent createEventState;
	private EventTemplate eventTemplate;
	
	public CreateEventPresenter(SuperPresenter superPresenter, EventTemplate eventTemplate) {
		super(superPresenter);
		this.eventTemplate = eventTemplate;
		
		this.createEventState = new CreateEvent();
		superPresenter.setState(createEventState);
		
		this.eventTemplate = eventTemplate;
		this.currentView = new CreateEventViewImplementaion(this, eventTemplate);
		superPresenter.addPage(currentView);
		
	}


	@Override
	public void submit(Boolean isPublic, int maxParticipants, Set<Account> set) {
		
		superPresenter.removePage(currentView);
		
		ArrayList<Account> accounts = new ArrayList<>();
//		for(Account a : set){
//			accounts.add(a);
//		}
		accounts.addAll(set);
		// Output des Accounts set: [Dover Ben, Hidepain Harald, Account{id=1, firstName='Jackson', lastName='Michael', email='jackson@mail.com', accountType=RELATIVE}]
		// Die Accounts in der MultiSelectListBox sind keine Accounts sondern schlussendlich nur Namen.
		accounts.add(superPresenter.getLoggedInAccount());
		Event event = new Event(eventTemplate, isPublic, maxParticipants, accounts);
		createEventState.saveEvent(event);
		
		new MyEventPresenter(superPresenter);
		
	}


	@Override
	public ArrayList<Account> getFriendsFromLoggedInAccount() {
		return createEventState.getFriends(superPresenter.getLoggedInAccount());
	}

}
