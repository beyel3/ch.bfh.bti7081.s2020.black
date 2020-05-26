package ch.bfh.bti7081.s2020.black.presenters;

import com.vaadin.flow.component.crud.Crud.NewEvent;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;
import ch.bfh.bti7081.s2020.black.model.stateModel.MyEvent;
import ch.bfh.bti7081.s2020.black.views.MyEventViewImplementation;

public class MyEventPresenter extends Presenter implements EventViewInterface {

	public MyEventPresenter(SuperPresenter superPresenter) {
		super(superPresenter);

		MyEvent myEventState = new MyEvent();
		superPresenter.setState(myEventState);
		
		currentView = new MyEventViewImplementation(this, myEventState.getEventListByAccount(superPresenter.getLoggedInAccount()));
		superPresenter.addPage(currentView);

	}

	@Override
	public void buttonClick(EventAction action) {

		superPresenter.removePage(currentView);

		switch (action) {
		
		case CHAT:
			new PostViewPresenter(superPresenter);
			break;
		case DETAILS:
			new PostViewPresenter(superPresenter);
			break;
		case MARKDONE:
			new PostViewPresenter(superPresenter);
			break;
		}
	}
}
