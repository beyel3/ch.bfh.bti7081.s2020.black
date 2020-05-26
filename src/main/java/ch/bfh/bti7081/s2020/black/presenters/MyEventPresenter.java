package ch.bfh.bti7081.s2020.black.presenters;

import java.util.ArrayList;

import com.vaadin.flow.component.dialog.Dialog;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.HardCoded;
import ch.bfh.bti7081.s2020.black.model.Post;
import ch.bfh.bti7081.s2020.black.model.stateModel.MyEvent;
import ch.bfh.bti7081.s2020.black.views.MyEventViewImplementation;
import ch.bfh.bti7081.s2020.black.views.PostViewImplementation;

public class MyEventPresenter extends Presenter implements EventViewInterface {

	private Dialog dialogPostView;
	private MyEvent myEventState;
	private Event selected;
	
	public MyEventPresenter(SuperPresenter superPresenter) {
		super(superPresenter);

		myEventState = new MyEvent();
		superPresenter.setState(myEventState);
		
		currentView = new MyEventViewImplementation<MyEventPresenter>(this);
		superPresenter.addPage(currentView);
		
		dialogPostView = new Dialog();
		dialogPostView.add(new PostViewImplementation<MyEventPresenter>(this));
		superPresenter.addPage(dialogPostView);

	}

	@Override
	public void buttonClick(EventAction action, Event selected) {

		this.selected = selected;
		
//		superPresenter.removePage(currentView);

		switch (action) {
		
		case OPENCHAT:
			dialogPostView.open();	
			break;
		case CLOSECHAT:
			dialogPostView.close();	
			break;
		case DETAILS:
			break;
		case MARKDONE:
			break;
		}
	}


	@Override
	public ArrayList<Post> getPosts() {
		// TODO Auto-generated method stub
		return null;
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
		
//		return new HardCoded().getEvent();
		return myEventState.getEventListByAccount(superPresenter.getLoggedInAccount());
	}
}
