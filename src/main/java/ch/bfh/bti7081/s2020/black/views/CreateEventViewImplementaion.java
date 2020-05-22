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

import ch.bfh.bti7081.s2020.black.model.Coreuser;
import ch.bfh.bti7081.s2020.black.model.HardCoded;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.presenters.CreateEventPresenter;


@Route(value = "CreateEvent", layout = MainView.class)
public class CreateEventViewImplementaion extends VerticalLayout implements HasUrlParameter<String> {


	@Override
    public void setParameter(BeforeEvent event, String parameter) {
		presenter.setEventTemplateByURLParameter(parameter);
    }
	
	private CreateEventPresenter presenter = new CreateEventPresenter(this);
	
	private TextField title;
	private TextArea description;
	private MultiSelectListBox<Tag> tags;
	private Checkbox publicEvent;
	private NumberField maxParticipants;
	private ArrayList<Coreuser> participants = new HardCoded().getCoreUser();
	
	public CreateEventViewImplementaion() {
		
		
		//Init
		title = new TextField();
		description = new TextArea();
		tags = new MultiSelectListBox<>();
		publicEvent = new Checkbox();
		maxParticipants = new NumberField();
		setSizeFull();
				
		
		//Event
		FormLayout FormLayoutLeft = new FormLayout();		
		title.setWidth("50%");
		title.setClearButtonVisible(true);
		title.setRequiredIndicatorVisible(true);
		
		description.setWidth("50%");
		description.setMinHeight("100px");
		description.setClearButtonVisible(true);
		description.setRequiredIndicatorVisible(true);
		
		maxParticipants.setHasControls(true);
		maxParticipants.setStep(1);
		maxParticipants.setMin(2);

		FormLayoutLeft.addFormItem(title, "Titel");
		FormLayoutLeft.addFormItem(description, "Beschreibung");
		FormLayoutLeft.addFormItem(tags, "Wähle Tags");
		FormLayoutLeft.addFormItem(publicEvent, "Öffentlich");
		FormLayoutLeft.addFormItem(maxParticipants, "Maximale Teilnehmer");
		FormLayoutLeft.setResponsiveSteps(new ResponsiveStep("40em", 1));
		
		//Add Patient
		FormLayout FormLayoutRight = new FormLayout();		
		MultiSelectListBox<Coreuser> participants = new MultiSelectListBox<Coreuser>();
		participants.setItems(this.participants);
		FormLayoutRight.addFormItem(participants, "Patienten hinzufügen:");
		
		
		// Button bar
		HorizontalLayout actions = new HorizontalLayout();
		
		Button save = new Button("Event erstellen");
		Button invite = new Button("Gäste einladen");
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
		
		
		HorizontalLayout FormLayouts = new HorizontalLayout();
		FormLayouts.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		FormLayouts.add(FormLayoutLeft, FormLayoutRight);
		
		VerticalLayout VerticalLayout = new VerticalLayout();
		VerticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		VerticalLayout.add(FormLayouts, actions);
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
