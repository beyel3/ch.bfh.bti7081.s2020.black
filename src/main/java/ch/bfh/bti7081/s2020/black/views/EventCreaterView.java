package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.presenters.EventPresenter;

import static ch.bfh.bti7081.s2020.black.views.MainView.eventPresenter;

@Route("EventCreaterView")
public class EventCreaterView extends HorizontalLayout {
	
//	boolean createdFromTemplate = true;
	private EventTemplate eventTemplate;
	private TextField title;
	private TextArea description;
	MultiSelectListBox<Tag> tags;
	
	public EventCreaterView() {
		
//		if (createdFromTemplate) {
//			
//			eventTemplate = eventPresenter.getEventTemplate();
//			
//			title.setValue(eventTemplate.getTitle());
//			description.setValue(eventTemplate.getDescription());
//			tags.setItems(eventTemplate.getTags());
//			
//			title.setReadOnly(true);
//			description.setReadOnly(true);
//			tags.setReadOnly(true);
//	
//		} else {}
		
		VerticalLayout VerticalLayout = new VerticalLayout();
		FormLayout FormLayout = new FormLayout();

		// Create the fields
		title = new TextField();
		description = new TextArea();
		
		tags = new MultiSelectListBox<>();
		tags.setItems(eventPresenter.getTags());
		
		Checkbox publicEvent = new Checkbox("public");
		TextField maxParticipants = new TextField();

		title.setRequiredIndicatorVisible(true);
		description.setRequiredIndicatorVisible(true);

		FormLayout.addFormItem(title, "Titel");
		FormLayout.addFormItem(description, "Beschreibung");
		FormLayout.addFormItem(tags, "Wähle Tags");
		FormLayout.addFormItem(publicEvent, "Öffentlich");
		FormLayout.addFormItem(maxParticipants, "Maximale Teilnehmer");

		// Button bar
		Label infoLabel = new Label();
		Button save = new Button("Save");
		Button reset = new Button("Reset");

		HorizontalLayout actions = new HorizontalLayout();
		actions.add(save, reset);
		save.getStyle().set("marginRight", "10px");

		// Event Handler
		save.addClickListener(event -> {
			infoLabel.setText("Event erstellt");			
		});

		reset.addClickListener(event -> {
			infoLabel.setText("");
		});

		VerticalLayout.add(FormLayout, actions);
		add(VerticalLayout);
	}
}
