package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;
import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface.EventAction;
import ch.bfh.bti7081.s2020.black.model.Post;

public class PostViewImplementation<T extends EventViewInterface> extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public PostViewImplementation(T presenter) {
		
		
		setSizeFull();
		VerticalLayout chatLayout = new VerticalLayout();
		
		for (Post p : presenter.getPosts(presenter.getSelectedEvent())) {
			VerticalLayout postLayout = new VerticalLayout();
			HorizontalLayout nameTimeLayout = new HorizontalLayout();
			
			TextField userName = new TextField();
			userName.setSizeFull();
			userName.setReadOnly(true);
			userName.setValue(p.getUserName());
			TextField timeStamp = new TextField();
			timeStamp.setSizeFull();
			timeStamp.setValue(p.getTimeString().substring(0,p.getTimeString().length()-4));
			timeStamp.setReadOnly(true);
			nameTimeLayout.add(userName, timeStamp);
			
			TextArea message = new TextArea();
			message.setWidth("50%");
			message.setValue(p.getMessage());
			message.setReadOnly(true);
			message.getStyle().set("margin-top", "2px");
			
			if (p.getAccountID() == presenter.getLoggedInAccountID()) { // if clause should be the current logged in user from the vaadin session
				message.getStyle().set("background-color", "#2f6f91");
			} else {
				message.getStyle().set("background-color", "#95a9c9");
			}
			
			postLayout.add(nameTimeLayout, message);
			chatLayout.add(postLayout);
			
		}
		
		HorizontalLayout poster = new HorizontalLayout();
		TextArea newPost = new TextArea();
		Button post = new Button("POST MESSAGE");
		post.addClickListener(event ->
		presenter.submitPost(newPost.getValue()));
		poster.add(newPost, post);
		
		Button close = new Button("CLOSE");
		close.addClickListener(event ->
		presenter.buttonClick(EventAction.CLOSECHAT, null));
		
		add(chatLayout, poster, close);
		
	}
}
