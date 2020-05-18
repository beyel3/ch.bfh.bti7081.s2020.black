package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

import ch.bfh.bti7081.s2020.black.model.Coreuser;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.HardCoded;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.presenters.CreateEventPresenter;
import ch.bfh.bti7081.s2020.black.presenters.CreateTemplatePresenter;
import ch.bfh.bti7081.s2020.black.presenters.EventTemplatePresenter;
import com.awesomecontrols.quickpopup.QuickPopup;
import com.awesomecontrols.quickpopup.QuickPopup.Align;

@Route(value = "EventTemplateView", layout = MainView.class)
public class EventTemplateViewImplementation extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	private EventTemplatePresenter eventTemplatePresenter = new EventTemplatePresenter(this);
	private QuickPopup eventTemplateCreator;
	private final VerticalLayout contentLayoutFirstRow;
	private final VerticalLayout contentLayoutSecondRow;
	private final VerticalLayout contentLayoutThirdRow;

	public EventTemplateViewImplementation() {
		
		setSizeFull();
		
		contentLayoutFirstRow = new VerticalLayout();
		contentLayoutFirstRow.setWidth("33%");
		contentLayoutFirstRow.setHeight("100%");
		contentLayoutFirstRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

		contentLayoutSecondRow = new VerticalLayout();
		contentLayoutSecondRow.setWidth("33%");
		contentLayoutSecondRow.setHeight("100%");
		contentLayoutSecondRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		
		contentLayoutThirdRow = new VerticalLayout();
		contentLayoutThirdRow.setWidth("33%");
		contentLayoutThirdRow.setHeight("100%");
		contentLayoutThirdRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		
		setFlexGrow(1, contentLayoutFirstRow, contentLayoutSecondRow, contentLayoutThirdRow);

//		createEventLayout.getStyle().set("border", "1px solid black");
		ArrayList<EventTemplate> eventTemplates = eventTemplatePresenter.getEventTemplates();
		VerticalLayout templates = new VerticalLayout();

		templates.setWidth("100%");
		templates.setMaxHeight("650px");
		templates.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
//		templates.getStyle().set("border", "1px solid black");
		templates.getStyle().set("overflowY", "auto");
		templates.getStyle().set("display", "block");

		for (EventTemplate t : eventTemplates) {

			VerticalLayout layout = new VerticalLayout();
			layout.getStyle().set("border", "1px solid #2f6f91");
			layout.getStyle().set("margin", "2px");

			TextField title = new TextField();
			title.setSizeFull();
			title.setValue(t.getTitle());
			title.setReadOnly(true);

			TextArea description = new TextArea();
			description.setSizeFull();
			description.setValue(t.getDescription());
			description.setReadOnly(true);

			MultiSelectListBox<Tag> tags = new MultiSelectListBox<Tag>();
			tags.setItems(t.getTags());
			tags.setReadOnly(true);

			ProgressBar progressBar = new ProgressBar();
			progressBar.setValue(t.getAvgRating() / 10);

			Button button = new Button("USE AS TEMPLATE");
			button.addClickListener(
					event -> getUI().ifPresent(ui -> ui.navigate("CreateEvent/" + t.getTemplateIDforURL())));

			layout.add(title, description, tags, progressBar, button);
			templates.add(layout);
		}
		
		Label labelOpenEventCreator = new Label("Wenn kein Template passt, kannst du hier ein neues Template erstellen um dein Event zu kreieren: ");
		Button buttonOpenEventCreator = new Button("Neues Template erstellen", event -> {
			eventTemplateCreator.show();
		});
		Label labelFilter = new Label("Wähle Tags um die Templates zu filtern: ");

		
		eventTemplateCreator = new QuickPopup(contentLayoutFirstRow.getElement(), new CreateTemplateViewImplementation());
		eventTemplateCreator.setAlign(Align.TOP_LEFT);
		
		contentLayoutFirstRow.add(labelFilter);
		contentLayoutSecondRow.add(templates, labelOpenEventCreator, buttonOpenEventCreator);
		contentLayoutThirdRow.add(labelOpenEventCreator, buttonOpenEventCreator);
		add(contentLayoutFirstRow, contentLayoutSecondRow,contentLayoutThirdRow);


	}
}
