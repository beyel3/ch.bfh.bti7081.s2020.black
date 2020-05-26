package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.Tag;
public class MyEventViewImplementation<T extends EventViewInterface> extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private HorizontalLayout eventLayout = new HorizontalLayout();

	public MyEventViewImplementation(T presenter) {
		
		Label labelMyEvents = new Label("My Events: ");
		labelMyEvents.getStyle().set("font-size", "24px");
		labelMyEvents.getStyle().set("font-weight", "bold");
		eventLayout.setWidth("100%");
		eventLayout.setMaxHeight("650px");
		eventLayout.getStyle().set("overflowX", "auto");
		eventLayout.setMargin(false);

		for (Event e: presenter.getMyEvents()) {
			
			String participantsHelper = new String(e.getParticipants().toString());
			participantsHelper = participantsHelper.substring(1, participantsHelper.length() - 1);
			participantsHelper = participantsHelper.replaceAll(", ", "\n");
			VerticalLayout layout = new VerticalLayout();
			layout.getStyle().set("border", "2px solid #2f6f91");

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
			buttonChat.addClickListener(event ->
			presenter.buttonClick(EventViewInterface.EventAction.OPENCHAT, e));
			
			Button buttonDetails = new Button("DETAILS");
			buttonDetails.addClickListener(event -> 
			presenter.buttonClick(EventViewInterface.EventAction.DETAILS, e));
			
			buttonLayout.add(buttonChat, buttonDetails);

			TextArea participants = new TextArea();
			participants.setSizeFull();
			participants.setValue(participantsHelper);
			participants.setReadOnly(true);
			layout.add(title, description, tags, progressBar, participants, buttonLayout);
			layout.getStyle().set("padding", "5px");
			layout.getStyle().set("margin-left", "2px");
			layout.getStyle().set("margin-right", "2px");
			layout.setMinWidth("350px");

			eventLayout.add(layout);
		}
		
		add(labelMyEvents, eventLayout);
	}
}
