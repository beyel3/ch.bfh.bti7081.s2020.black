package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.model.HardCoded;
import ch.bfh.bti7081.s2020.black.model.Post;

@Route(value = "Post", layout = MainView.class)
public class PostViewImplementation extends VerticalLayout {

	private HardCoded postsHardCoded = new HardCoded();
	private static final long serialVersionUID = 1L;

	private ArrayList<Post> posts = new ArrayList<>(postsHardCoded.getPosts());
	public PostViewImplementation() {
		setSizeFull();
		VerticalLayout chatLayout = new VerticalLayout();
		
		for (Post p : posts) {
			VerticalLayout postLayout = new VerticalLayout();
			HorizontalLayout nameTimeLayout = new HorizontalLayout();
			
			TextField userName = new TextField();
			userName.setSizeFull();
			userName.setReadOnly(true);
			userName.setValue(p.getUser());
			TextField timeStamp = new TextField();
			timeStamp.setSizeFull();
			timeStamp.setValue(p.getTimeString().substring(0,p.getTimeString().length() - 10));
			timeStamp.setReadOnly(true);
			nameTimeLayout.add(userName, timeStamp);
			
			TextArea message = new TextArea();
			message.setWidth("50%");
			message.setValue(p.getMessage());
			message.setReadOnly(true);
			message.getStyle().set("margin-top", "2px");
			
			if (p.getUser().equalsIgnoreCase("Mario Schläppi")) { // if clause should be the current logged in user from the vaadin session
				message.getStyle().set("background-color", "#2f6f91");
			} else {
				message.getStyle().set("background-color", "#95a9c9");
			}
			
			postLayout.add(nameTimeLayout, message);
			chatLayout.add(postLayout);
			
		}
		add(chatLayout);
		
	}
}
