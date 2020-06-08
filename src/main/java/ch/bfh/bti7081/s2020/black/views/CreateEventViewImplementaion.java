package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;

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

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.CreateEventInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;


public class CreateEventViewImplementaion<T extends CreateEventInterface> extends VerticalLayout {

	
	private T presenter;
	
	private TextField title;
	private TextArea description;
	private MultiSelectListBox<Tag> tags;
	private Checkbox publicEvent;
	private NumberField maxParticipants;
	private ArrayList<Account> participants;
	private EventTemplate eventTemplate;

	
	public CreateEventViewImplementaion(T createEventPresenter, EventTemplate eventTemplate) {
		this.eventTemplate = eventTemplate;
		this.presenter = createEventPresenter;

		
		//Init
		title = new TextField();
		description = new TextArea();
		tags = new MultiSelectListBox<>();
		publicEvent = new Checkbox();
		maxParticipants = new NumberField();
		MultiSelectListBox<Account> participants = new MultiSelectListBox<Account>();
		participants.setItems(presenter.getFriendsFromLoggedInAccount());
		setSizeFull();
				
		
		//Event
		FormLayout FormLayoutLeft = new FormLayout();		
		title.setWidth("50%");
		title.setReadOnly(true);
		title.setValue(eventTemplate.getTitle());
		
		description.setWidth("50%");
		description.setMinHeight("100px");
		description.setReadOnly(true);
		description.setValue(eventTemplate.getDescription());
		
		tags.setItems(eventTemplate.getTags());		
		tags.setReadOnly(true);
		
		maxParticipants.setHasControls(true);
		maxParticipants.setStep(1);
		maxParticipants.setMin(2);
		
		
		Button save = new Button("CREATE EVENT");
		save.getStyle().set("marginRight", "10px");

		// Event Handler
		save.addClickListener(event -> {
			presenter.submit(
					publicEvent.getValue(),
					(int) Math.round(maxParticipants.getValue()),
					participants.getSelectedItems());

		});

		FormLayoutLeft.addFormItem(title, "Title");
		FormLayoutLeft.addFormItem(description, "Description");
		FormLayoutLeft.addFormItem(tags, "Tags");
		FormLayoutLeft.addFormItem(publicEvent, "Public");
		FormLayoutLeft.addFormItem(maxParticipants, "Maximum Participants");
		FormLayoutLeft.addFormItem(save, "");
		FormLayoutLeft.setResponsiveSteps(new ResponsiveStep("40em", 1));
		
		//Add Patient
		FormLayout FormLayoutRight = new FormLayout();		
		FormLayoutRight.addFormItem(participants, "Add Patient:");
		
		HorizontalLayout FormLayouts = new HorizontalLayout();
		FormLayouts.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		FormLayouts.add(FormLayoutLeft, FormLayoutRight);
		
		VerticalLayout VerticalLayout = new VerticalLayout();
		VerticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		VerticalLayout.add(FormLayouts);
		add(VerticalLayout);
	}
}
