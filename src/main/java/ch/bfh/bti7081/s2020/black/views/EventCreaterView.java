package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.FormItem;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.model.HardCoded;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.presenters.EventPresenter;

@Route("EventCreaterView")
public class EventCreaterView extends HorizontalLayout {

	public EventCreaterView() {
		
		EventPresenter presenter = new EventPresenter();

		VerticalLayout VerticalLayout = new VerticalLayout();

		FormLayout FormLayout = new FormLayout();

		// Create the fields
		TextField title = new TextField();
		TextArea description = new TextArea();
		
		MultiSelectListBox<Tag> listBox = new MultiSelectListBox<>();
		listBox.setItems(presenter.getTags());
		
		Checkbox publicEvent = new Checkbox("public");

		title.setRequiredIndicatorVisible(true);
		description.setRequiredIndicatorVisible(true);

		FormLayout.addFormItem(title, "Titel");
		FormLayout.addFormItem(description, "Beschreibung");
		FormLayout.addFormItem(listBox, "Wähle Tags");
		FormLayout.addFormItem(publicEvent, "Öffentlich");

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
