package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.HardCoded;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.presenters.AccountPresenter;

@Route(value = "MyEvents", layout = MainView.class)
@RouteAlias(value = "AccountView", layout = MainView.class)
@PageTitle("MyEvents")

public class AccountViewImplementation extends VerticalLayout {

	private HardCoded eventsHardCoded = new HardCoded();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Event> events = new ArrayList<>(eventsHardCoded.getEvent());
	private HorizontalLayout eventLayout = new HorizontalLayout();


	public AccountViewImplementation() {
		Label labelMyEvents = new Label(
				"My Events: ");
		labelMyEvents.getStyle().set("font-size", "24px");
		labelMyEvents.getStyle().set("font-weight", "bold");
		eventLayout.setWidth("100%");
//		eventLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
//		eventLayout.getStyle().set("border", "1px solid black");
		eventLayout.getStyle().set("overflowX", "auto");
//		eventLayout.getElement(layout).getStyle().set("width", "100px");
//		eventLayout.getStyle().set("display", "block");
		createEventBar();
		add(labelMyEvents, eventLayout);

	}

	private HorizontalLayout createEventBar() {
		for (Event e : events) {
			String participantsHelper = new String(e.getParticipants().toString());
			participantsHelper = participantsHelper.substring(1, participantsHelper.length() - 1);
			participantsHelper = participantsHelper.replaceAll(", ", "\n");
			VerticalLayout layout = new VerticalLayout();
			layout.getStyle().set("border", "1px solid #2f6f91");
			layout.getStyle().set("margin", "2px");

			TextField title = new TextField();
			title.setSizeFull();
			title.setValue(e.getEventTemplate().getTitle());
			title.setReadOnly(true);

			TextArea description = new TextArea();
			description.setSizeFull();
			description.setValue(e.getEventTemplate().getDescription());
			description.setReadOnly(true);

			MultiSelectListBox<Tag> tags = new MultiSelectListBox<Tag>();
			tags.setItems(e.getEventTemplate().getTags());
			tags.setReadOnly(true);

			ProgressBar progressBar = new ProgressBar();
			progressBar.setValue(e.getEventTemplate().getAvgRating() / 10);
			
			HorizontalLayout buttonLayout = new HorizontalLayout();
			Button buttonChat = new Button("CHAT");
			buttonChat.addClickListener(event -> getUI()
					.ifPresent(ui -> ui.navigate("MyEvents")));
			Button buttonDetails = new Button("DETAILS");
			buttonDetails.addClickListener(event -> getUI()
					.ifPresent(ui -> ui.navigate("MyEvents")));
			buttonLayout.add(buttonChat, buttonDetails);
			
			TextArea participants = new TextArea();
			participants.setSizeFull();
			participants.setValue(participantsHelper);
			participants.setReadOnly(true);

			layout.add(title, description, tags, progressBar, participants, buttonLayout);
			layout.setWidth("100%");
			eventLayout.add(layout);
		}
		return eventLayout;
	}
}
