package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;

@Route("EventCreaterView")
public class EventCreaterViewImplementaion extends HorizontalLayout implements EventCreaterView {
	
//	boolean createdFromTemplate = true;
	private EventTemplate eventTemplate;
	private TextField title;
	private TextArea description;
	MultiSelectListBox<Tag> tags;
	
	public EventCreaterViewImplementaion() {
		
		VerticalLayout VerticalLayout = new VerticalLayout();
		FormLayout FormLayout = new FormLayout();

		// Create the fields
		title = new TextField();
		description = new TextArea();
		tags = new MultiSelectListBox<>();
		
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

	@Override
	public void setTitle(String title) {
		this.title.setValue(title);
	}

	@Override
	public void setDescription(String description) {
		this.description.setValue(description);
	}

	@Override
	public void setTags(List<Tag> tags) {
		this.tags.setItems(tags);		
	}

	@Override
	public void setRating(double avgRating) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(EventCreaterViewListener listener) {
				
	}
}
