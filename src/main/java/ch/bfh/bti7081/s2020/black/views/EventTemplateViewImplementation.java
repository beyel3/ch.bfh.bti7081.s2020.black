package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
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
	ArrayList<EventTemplate> eventTemplates;
//	private final VerticalLayout contentLayoutThirdRow;
//	private final VerticalLayout templates;

	public EventTemplateViewImplementation() {

		setSizeFull();

		contentLayoutFirstRow = new VerticalLayout();
		contentLayoutFirstRow.setWidth("80%");
		contentLayoutFirstRow.setHeight("100%");
		contentLayoutFirstRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

		contentLayoutSecondRow = new VerticalLayout();
		contentLayoutSecondRow.setWidth("20%");
		contentLayoutSecondRow.setHeight("100%");
		contentLayoutSecondRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

		setFlexGrow(1, contentLayoutFirstRow, contentLayoutSecondRow);

		eventTemplates = eventTemplatePresenter.getEventTemplates();

		Grid<EventTemplate> grid = new Grid<>();
		ListDataProvider<EventTemplate> dataProvider = new ListDataProvider<>(eventTemplates);
		grid.setDataProvider(dataProvider);

		Grid.Column<EventTemplate> titleColumn = grid.addColumn(EventTemplate::getTitle).setHeader("Title");
		Grid.Column<EventTemplate> descriptionColumn = grid.addColumn(EventTemplate::getDescription)
				.setHeader("Description");
		Grid.Column<EventTemplate> tagColumn = grid.addColumn(EventTemplate::getTags).setHeader("Tags");
		Grid.Column<EventTemplate> ratingColumn = grid.addColumn(EventTemplate::getAvgRating).setHeader("Rating");
		grid.addComponentColumn(item -> createUseAsTemplateButton(grid, item))
        .setHeader("Use as template");

		HeaderRow filterRow = grid.appendHeaderRow();
		// First filter for rating
		TextField titleField = new TextField();
		titleField.addValueChangeListener(event -> dataProvider.addFilter(
				eventTemplate -> StringUtils.containsIgnoreCase(eventTemplate.getTitle(), titleField.getValue())));

		titleField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(titleColumn).setComponent(titleField);
		titleField.setSizeFull();
		titleField.setPlaceholder("Filter");

		// Second Filter for description
		TextField descriptionField = new TextField();
		descriptionField.addValueChangeListener(event -> dataProvider.addFilter(eventTemplate -> StringUtils
				.containsIgnoreCase(eventTemplate.getDescription(), descriptionField.getValue())));

		descriptionField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(descriptionColumn).setComponent(descriptionField);
		descriptionField.setSizeFull();
		descriptionField.setPlaceholder("Filter");

		// Thrid Filter for tags
		TextField tagField = new TextField();
//		tagField.addValueChangeListener(event -> dataProvider.addFilter(eventTemplate -> StringUtils.containsIgnoreCase(eventTemplate.getTags(), tagField.getValue())));

		tagField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(tagColumn).setComponent(tagField);
		tagField.setSizeFull();
		tagField.setPlaceholder("Filter");

		grid.setWidth("100%");
		grid.setMaxHeight("650px");
		grid.getStyle().set("overflowY", "auto");


		dialogCreateEvent = new Dialog();
		dialogCreateEvent.add(new CreateTemplateViewImplementation());
		Label labelOpenEventCreator = new Label(
				"If no template fits, you can create a new template here to create your event: ");
		Button buttonOpenEventCreator = new Button("Create New Template", event -> {
			dialogCreateEvent.open();
		});

		contentLayoutSecondRow.add(labelOpenEventCreator, buttonOpenEventCreator);
		contentLayoutFirstRow.add(grid);
		add(contentLayoutFirstRow, contentLayoutSecondRow);
	}

	private Button createUseAsTemplateButton(Grid<EventTemplate> grid, EventTemplate item) {
		Button buttonUseAsTemplate = new Button("USE AS TEMPLATE");
		for (EventTemplate t : eventTemplates) {			
			buttonUseAsTemplate.addClickListener(
					event -> getUI().ifPresent(ui -> ui.navigate("CreateEvent/" + t.getTemplateIDforURL())));
		}
		return buttonUseAsTemplate;
	}
}
