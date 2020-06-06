package ch.bfh.bti7081.s2020.black.presenters;

import java.util.ArrayList;
import java.util.Set;

import com.vaadin.flow.component.dialog.Dialog;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.JoinPublicEventInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.stateModel.JoinPublicEvents;
import ch.bfh.bti7081.s2020.black.views.AddPatientView;
import ch.bfh.bti7081.s2020.black.views.CreateTemplateViewImplementation;
import ch.bfh.bti7081.s2020.black.views.JoinPublicEventViewImplementation;

public class JoinPublicEventPresenter extends Presenter implements JoinPublicEventInterface {

	private JoinPublicEvents joinPublicEventsState;
	private Dialog dialogCreateEvent;
	private Event eventToJoin;

	public JoinPublicEventPresenter(SuperPresenter superPresenter) {
		super(superPresenter);

		joinPublicEventsState = new JoinPublicEvents();
		superPresenter.setState(joinPublicEventsState);

		currentView = new JoinPublicEventViewImplementation(this);
		superPresenter.addPage(currentView);

		dialogCreateEvent = new Dialog();
		dialogCreateEvent.add(new AddPatientView(this));
		superPresenter.addPage(dialogCreateEvent);

	}

	@Override
	public ArrayList<Event> getOpenPublicEvents() {
		return joinPublicEventsState.getOpenPublicEvents();
	}

	@Override
	public void selectEvent(Event event) {
		superPresenter.removePage(currentView);
		dialogCreateEvent.open();
		eventToJoin = event;
	}

	@Override
	public ArrayList<Account> getFriends() {
		return joinPublicEventsState.getFriends(superPresenter.getLoggedInAccount());
	}

	@Override
	public void joinPublicEvent(Set<Account> participants) {
		ArrayList<Account> list = new ArrayList<>();
		for (Account a : participants) {
			list.add(a);
		}
		joinPublicEventsState.joinPublicEvent(list, eventToJoin);
		
		superPresenter.removePage(dialogCreateEvent);
		new MyEventPresenter(superPresenter);
	}

}
