package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
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
	private Dialog dialogCreateEvent;
	private final VerticalLayout contentLayoutFirstRow;
	private final VerticalLayout contentLayoutSecondRow;
	private final VerticalLayout contentLayoutThirdRow;
	private final VerticalLayout templates;

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

		ArrayList<EventTemplate> eventTemplates = eventTemplatePresenter.getEventTemplates();

		templates = new VerticalLayout();
		templates.setWidth("100%");
		templates.setMaxHeight("650px");
		templates.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
//		templates.getStyle().set("border", "1px solid black");
		templates.getStyle().set("overflowY", "auto");
		templates.getStyle().set("display", "block");

		VerticalLayout filter = new VerticalLayout();
		filter.setWidth("100%");
		filter.setMaxHeight("650px");
		filter.getStyle().set("overflowY", "auto");
		filter.getStyle().set("display", "block");

		ArrayList<String> tagNames = new ArrayList<String>();

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

			ArrayList<Tag> filterTags = new ArrayList<Tag>(t.getTags());
			for (Tag tagString : filterTags) {
				String titleTagButton = tagString.getTagName();

				boolean containsTagName = tagNames.contains(titleTagButton);
				if (!containsTagName) {
					Button tagButton = new Button(titleTagButton, event -> {
						filterTemplate();
					});
					tagButton.getStyle().set("margin", "2px");
					filter.add(tagButton);
					tagNames.add(titleTagButton);
				}

			}

		}
		
		dialogCreateEvent = new Dialog();
		dialogCreateEvent.add(new CreateTemplateViewImplementation());
		Label labelOpenEventCreator = new Label(
				"If no template fits, you can create a new template here to create your event: ");
		Button buttonOpenEventCreator = new Button("Create New Template", event -> {
			dialogCreateEvent.open();
		});
		Label labelFilter = new Label("Select tags to filter the templates: ");

		contentLayoutFirstRow.add(labelFilter, filter);
		contentLayoutSecondRow.add(templates, labelOpenEventCreator, buttonOpenEventCreator);
		contentLayoutThirdRow.add(labelOpenEventCreator, buttonOpenEventCreator);
		add(contentLayoutFirstRow, contentLayoutSecondRow, contentLayoutThirdRow);

	}
	
	private void filterTemplate() {
		if(templates.isVisible()) {
			templates.setVisible(false);
		} else {
			templates.setVisible(true);
		}
	}
}
