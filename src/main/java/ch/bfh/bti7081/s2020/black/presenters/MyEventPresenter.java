package ch.bfh.bti7081.s2020.black.presenters;

import java.util.ArrayList;

import com.vaadin.flow.component.dialog.Dialog;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.Patient;
import ch.bfh.bti7081.s2020.black.model.Post;
import ch.bfh.bti7081.s2020.black.model.stateModel.MyEvent;
import ch.bfh.bti7081.s2020.black.views.AddFriendsViewImplementation;
import ch.bfh.bti7081.s2020.black.views.MyEventViewImplementation;
import ch.bfh.bti7081.s2020.black.views.PatientInfoViewImplementation;
import ch.bfh.bti7081.s2020.black.views.PostViewImplementation;

public class MyEventPresenter extends Presenter implements EventViewInterface {

	private Dialog dialogPostView;
	private Dialog dialogAddFriendsView;
	private Dialog dialogPatientInfoView;
	private MyEvent myEventState;
	private Event selected;

	
	public MyEventPresenter(SuperPresenter superPresenter) {
		super(superPresenter);

		myEventState = new MyEvent();
		superPresenter.setState(myEventState);
		
		currentView = new MyEventViewImplementation<MyEventPresenter>(this);
		superPresenter.addPage(currentView);

	}

	@Override
	public void buttonClick(EventAction action, Event selected) {

		this.selected = selected;
			
		switch (action) {
		
		case OPENCHAT:
			dialogPostView = new Dialog();
			dialogPostView.add(new PostViewImplementation<MyEventPresenter>(this));
			superPresenter.addPage(dialogPostView);
			dialogPostView.open();	
			break;
		case CLOSECHAT:
			dialogPostView.close();	
			break;
		case ADDFRIEND:
			dialogAddFriendsView = new Dialog();
			dialogAddFriendsView.add(new AddFriendsViewImplementation<MyEventPresenter>(this));
			superPresenter.addPage(dialogAddFriendsView);
			dialogAddFriendsView.open();	
			break;
		case MARKDONE:
			superPresenter.removePage(currentView);
			new CloseEventPresenter(superPresenter, selected);
			break;
		}
	}


	@Override
	public ArrayList<Post> getPosts(Event event) {
		return myEventState.getPostsByEventID(event);
	}

	@Override
	public void submitPost(String post) {
		myEventState.savePost(post, superPresenter.getLoggedInAccount(), selected);
		
		superPresenter.removePage(dialogPostView);
		
		dialogPostView = new Dialog();
		dialogPostView.add(new PostViewImplementation<MyEventPresenter>(this));
		superPresenter.addPage(dialogPostView);
		dialogPostView.open();
	}

	@Override
	public ArrayList<Event> getMyEvents() {
		return myEventState.getEventListByAccount(superPresenter.getLoggedInAccount());
	}
	
	@Override
	public ArrayList<Event> getMyOpenEvents() {
		return myEventState.getOpenEventListByAccount(superPresenter.getLoggedInAccount());
	}
	
	@Override
	public ArrayList<Event> getMyDoneEvents() {
		return myEventState.getDoneEventListByAccount(superPresenter.getLoggedInAccount());
	}

	@Override
	public Event getSelectedEvent() {
		return selected;
	}
	
	@Override
	public ArrayList<Account> getMyFriends() {
		return myEventState.getFriends(superPresenter.getLoggedInAccount());
	}

	@Override
	public ArrayList<Patient> getAccounts() {
		return myEventState.getPatientListWithNoRel(superPresenter.getLoggedInAccount());
	}

	@Override
	public void addFriend(Patient patient) {
		myEventState.addFriend(patient, superPresenter.getLoggedInAccount());
		superPresenter.removePage(currentView);
		superPresenter.removePage(dialogAddFriendsView);
		superPresenter.addPage(currentView);
		dialogAddFriendsView = new Dialog();
		dialogAddFriendsView.add(new AddFriendsViewImplementation<MyEventPresenter>(this));
		superPresenter.addPage(dialogAddFriendsView);
		dialogAddFriendsView.open();
	}

	@Override
	public void enablePatientInfoView(Account acc) {
		dialogPatientInfoView = new Dialog();
		dialogPatientInfoView.add(new PatientInfoViewImplementation(this, acc));
		superPresenter.addPage(dialogPatientInfoView);
		dialogPatientInfoView.open();	
	}

	@Override
	public ArrayList<Event> getPatientEvents(Account acc) {
		return myEventState.getOpenEventListByAccount(acc);
	}
}
