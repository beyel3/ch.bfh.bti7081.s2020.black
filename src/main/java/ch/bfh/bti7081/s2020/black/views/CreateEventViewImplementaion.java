package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.presenters.CreateEventPresenter;


@Route(value = "CreateEvent", layout = MainView.class)
public class CreateEventViewImplementaion extends VerticalLayout implements HasUrlParameter<String> {


	@Override
    public void setParameter(BeforeEvent event, String parameter) {
		presenter.setEventTemplateByURLParameter(parameter);
    }
	
	private CreateEventPresenter presenter = new CreateEventPresenter(this);
	
	private TextField title = new TextField();
	private TextArea description = new TextArea();
	private MultiSelectListBox<Tag> tags = new MultiSelectListBox<>();
	private Checkbox publicEvent = new Checkbox();
	private NumberField maxParticipants = new NumberField();
	
	public CreateEventViewImplementaion() {
		
		maxParticipants.setHasControls(true);
		maxParticipants.setStep(1);
		maxParticipants.setMin(2);

		setSizeFull();
				
		VerticalLayout VerticalLayout = new VerticalLayout();
		FormLayout FormLayout = new FormLayout();		
		
		title.setWidth("50%");
		title.setClearButtonVisible(true);
		
		description.setWidth("50%");
		description.setMinHeight("100px");
		description.setClearButtonVisible(true);

		title.setRequiredIndicatorVisible(true);
		description.setRequiredIndicatorVisible(true);

		FormLayout.addFormItem(title, "Titel");
		FormLayout.addFormItem(description, "Beschreibung");
		FormLayout.addFormItem(tags, "Wähle Tags");
		FormLayout.addFormItem(publicEvent, "Öffentlich");
		FormLayout.addFormItem(maxParticipants, "Maximale Teilnehmer");
		FormLayout.setResponsiveSteps(
		        new ResponsiveStep("40em", 1));
		
		// Button bar
		Button save = new Button("Event erstellen");
		Button invite = new Button("Gäste einladen");

		HorizontalLayout actions = new HorizontalLayout();
		actions.add(save, invite);
		save.getStyle().set("marginRight", "10px");
		actions.getStyle().set("marginLeft", "50px");

		// Event Handler
		save.addClickListener(event -> {
			presenter.saveEvent(publicEvent.getValue(), (int) Math.round(maxParticipants.getValue()));
			UI.getCurrent().navigate("LandingPage");
			
		});

		invite.addClickListener(event -> {

		});
		
		VerticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		VerticalLayout.add(FormLayout, actions);
		add(VerticalLayout);
	}
	
	public void setTemplateInfo(String title, String description, List<Tag> tags) {
		
		this.title.setValue(title);
		this.description.setValue(description);
		this.tags.setItems(tags);
		
		this.title.setReadOnly(true);
		this.description.setReadOnly(true);
		this.tags.setReadOnly(true);
		
	}
}
