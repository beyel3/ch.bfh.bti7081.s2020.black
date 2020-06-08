package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.Tag;

public class PatientInfoViewImplementation<T extends EventViewInterface> extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	private VerticalLayout eventLayout = new VerticalLayout();
	
	public PatientInfoViewImplementation(T presenter, Account acc) {

		Label labelPatientEvents = new Label("Patient Events: ");
		labelPatientEvents.getStyle().set("font-size", "24px");
		labelPatientEvents.getStyle().set("font-weight", "bold");
		
		eventLayout.setWidth("80%");
		eventLayout.setMaxHeight("60vh");
		eventLayout.getStyle().set("overflowY", "auto");
		eventLayout.getStyle().set("display", "block");
		
		setMaxHeight("900px");
		setMinHeight("500px");
		setMinWidth("500px");
		
		ArrayList<Event> patientEvents = presenter.getPatientEvents(acc);
		
		for (Event e : patientEvents) {

			// System.out.println(e.getEventTemplate().getId());

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

			TextArea participants = new TextArea();
			participants.setSizeFull();
			participants.setValue(participantsHelper);
			participants.setReadOnly(true);
			layout.add(title, description, tags, progressBar, participants);
			layout.getStyle().set("padding", "5px");
			layout.getStyle().set("margin-left", "2px");
			layout.getStyle().set("margin-right", "2px");
			layout.setMinWidth("350px");

			eventLayout.add(layout);
		}
	}
}
