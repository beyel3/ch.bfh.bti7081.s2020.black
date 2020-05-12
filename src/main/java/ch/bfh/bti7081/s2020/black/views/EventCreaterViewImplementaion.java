package ch.bfh.bti7081.s2020.black.views;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
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


@Route(value = "Event", layout = MainView.class)
public class EventCreaterViewImplementaion extends VerticalLayout implements EventCreaterView, HasUrlParameter<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
        }
    }
	
	private int eventTemplateID;
	private EventPresenter eventPresenter = new EventPresenter();
	private EventTemplate eventTemplate;
	private TextField title = new TextField();
	private TextArea description = new TextArea();
	MultiSelectListBox<Tag> tags = new MultiSelectListBox<>();
	
	public EventCreaterViewImplementaion() {
		
		setSizeFull();
		tags.setItems(eventPresenter.getTags());
				
		VerticalLayout VerticalLayout = new VerticalLayout();
		FormLayout FormLayout = new FormLayout();		
		
		Checkbox publicEvent = new Checkbox("public");
		TextField maxParticipants = new TextField();
		
		TextField title = new TextField();
		title.setWidth("50%");
		title.setClearButtonVisible(true);
		
		TextArea description = new TextArea();
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
		Button save = new Button("Save");
		Button reset = new Button("Reset");

		HorizontalLayout actions = new HorizontalLayout();
		actions.add(save, reset);
		save.getStyle().set("marginRight", "10px");
		actions.getStyle().set("marginLeft", "50px");

		// Event Handler
		save.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("LandingPage")));

		reset.addClickListener(event -> title.setValue(""));
		
		VerticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
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
