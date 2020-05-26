package ch.bfh.bti7081.s2020.black.presenters;

import java.util.ArrayList;

import com.vaadin.flow.component.crud.Crud.NewEvent;
import com.vaadin.flow.component.dialog.Dialog;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;
import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.PostInterface;
import ch.bfh.bti7081.s2020.black.model.Post;
import ch.bfh.bti7081.s2020.black.model.stateModel.MyEvent;
import ch.bfh.bti7081.s2020.black.views.MyEventViewImplementation;

public class MyEventPresenter extends Presenter implements EventViewInterface, PostInterface {

	private Dialog dialogCreateEvent;
	private MyEvent myEventState;
	
	public MyEventPresenter(SuperPresenter superPresenter) {
		super(superPresenter);

		myEventState = new MyEvent();
		superPresenter.setState(myEventState);
		
		currentView = new MyEventViewImplementation<MyEventPresenter>(this, myEventState.getEventListByAccount(superPresenter.getLoggedInAccount()));
		superPresenter.addPage(currentView);

	}

	@Override
	public void buttonClick(EventAction action) {

//		superPresenter.removePage(currentView);

		switch (action) {
		
		case CHAT:
			new PostViewPresenter(superPresenter);
			break;
		case DETAILS:
			break;
		case MARKDONE:
			new PostViewPresenter(superPresenter);
			break;
		}
	}

	@Override
	public void openChat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Post> getPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submitPost(String post) {
		// TODO Auto-generated method stub
		
	}
}
