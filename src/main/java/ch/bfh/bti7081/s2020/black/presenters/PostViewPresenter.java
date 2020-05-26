package ch.bfh.bti7081.s2020.black.presenters;

import java.util.ArrayList;

import com.vaadin.flow.component.dialog.Dialog;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.PostInterface;
import ch.bfh.bti7081.s2020.black.model.Post;
import ch.bfh.bti7081.s2020.black.model.stateModel.MyEvent;
import ch.bfh.bti7081.s2020.black.views.PostViewImplementation;

public class PostViewPresenter extends Presenter implements PostInterface {
	
	private MyEvent myEventState;
	private Dialog dialogCreateEvent;

	public PostViewPresenter(SuperPresenter superPresenter) {
		super(superPresenter);

		myEventState = new MyEvent();
		superPresenter.setState(myEventState);


		dialogCreateEvent = new Dialog();
		dialogCreateEvent.add(new PostViewImplementation<PostViewPresenter>(this));
		superPresenter.addPage(dialogCreateEvent);

	}

	@Override
	public void openChat() {
		dialogCreateEvent.open();
		
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
