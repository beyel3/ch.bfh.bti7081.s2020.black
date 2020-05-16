package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;

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

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.presenters.EventCreaterPresenter;


@Route(value = "Event", layout = MainView.class)
public class EventCreaterViewImplementaion extends VerticalLayout implements HasUrlParameter<String> {


	@Override
    public void setParameter(BeforeEvent event,
            @OptionalParameter String parameter) {
        if (parameter != null) {
        	
           eventTemplateID = Integer.parseInt(parameter);
           eventTemplate = eventPresenter.getEventTemplateFromID(eventTemplateID);
           
			title.setValue(eventTemplate.getTitle());
			title.setReadOnly(true);
			description.setValue(eventTemplate.getDescription());
			description.setReadOnly(true);
			tags.setItems(eventTemplate.getTags());
			tags.setReadOnly(true);
			createdFromTemplate = true;
        }
    }
	
	private boolean createdFromTemplate;
	private int eventTemplateID;
	private EventCreaterPresenter eventPresenter = new EventCreaterPresenter();
	private EventTemplate eventTemplate;
	private TextField title = new TextField();
	private TextArea description = new TextArea();
	private MultiSelectListBox<Tag> tags = new MultiSelectListBox<>();
	private Checkbox publicEvent = new Checkbox();
	private NumberField maxParticipants = new NumberField();
	
	public EventCreaterViewImplementaion() {
		
		maxParticipants.setHasControls(true);
		maxParticipants.setStep(1);
		maxParticipants.setMin(2);

		setSizeFull();

		tags.setItems(eventPresenter.getTags());
				
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
			
			if(createdFromTemplate) {
			eventPresenter.saveEventWithTemplate(
					eventTemplate,
					publicEvent.getValue(),
					(int) Math.round(maxParticipants.getValue()),
					null);
			}
			else {
				
				ArrayList<Tag> selectedTags = new ArrayList<Tag>();
				for(Tag t :	tags.getSelectedItems()) {
					selectedTags.add(t);
				}
				
				eventPresenter.saveEventCreateNewEventTemplate(
						title.getValue(),
						description.getValue(),
						selectedTags,
						publicEvent.getValue(),
						(int) Math.round(maxParticipants.getValue()),
						null);
				
			}
			
			UI.getCurrent().navigate("LandingPage");
			
		});

		invite.addClickListener(event -> {

		});
		
		VerticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		VerticalLayout.add(FormLayout, actions);
		add(VerticalLayout);
	}
}
