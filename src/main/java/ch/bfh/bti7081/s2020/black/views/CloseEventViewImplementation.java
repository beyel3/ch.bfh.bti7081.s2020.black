package ch.bfh.bti7081.s2020.black.views;

import java.io.ByteArrayInputStream;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.StreamResource;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.Tag;

public class CloseEventViewImplementation<T extends CloseEventViewInterface> extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private Event event;

	private HorizontalLayout eventLayout = new HorizontalLayout();

	public CloseEventViewImplementation(T presenter) {

		
//		byte[] imageBytes;
//		StreamResource resource = new StreamResource("dummyImageName.jpg", () -> new ByteArrayInputStream(imageBytes));
//		Image image = new Image(resource, "dummy image");
//		add(image);
		
		event = presenter.getEvent();

		Label labelMyEvents = new Label("My Event: ");
		labelMyEvents.getStyle().set("font-size", "24px");
		labelMyEvents.getStyle().set("font-weight", "bold");

		eventLayout.setWidth("100%");
		eventLayout.setMaxHeight("650px");
		eventLayout.getStyle().set("overflowX", "auto");
		eventLayout.setMargin(false);

		String participantsHelper = new String(event.getParticipants().toString());
		participantsHelper = participantsHelper.substring(1, participantsHelper.length() - 1);
		participantsHelper = participantsHelper.replaceAll(", ", "\n");
		VerticalLayout layout = new VerticalLayout();
		layout.getStyle().set("border", "2px solid #2f6f91");

		TextField title = new TextField();
		title.setSizeFull();
		title.setValue(event.getEventTemplate().getTitle());
		title.setReadOnly(true);

		TextArea description = new TextArea();
		description.setSizeFull();
		description.setValue(event.getEventTemplate().getDescription());
		description.setReadOnly(true);

		MultiSelectListBox<Tag> tags = new MultiSelectListBox<Tag>();
		tags.setItems(event.getEventTemplate().getTags());
		tags.setReadOnly(true);

		ProgressBar progressBar = new ProgressBar();
		progressBar.setValue(event.getEventTemplate().getAvgRating() / 10);

		HorizontalLayout buttonLayout = new HorizontalLayout();

		Button buttonChat = new Button("CLOSE");
		buttonChat.addClickListener(event -> 
		presenter.buttonClick(EventViewInterface.EventAction.OPENCHAT, event));

		Button buttonDetails = new Button("UPLOAD PICTURE");
		buttonDetails.addClickListener(event -> presenter.buttonClick(EventViewInterface.EventAction.DETAILS, event));

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

		add(labelMyEvents, eventLayout);
	}
}
