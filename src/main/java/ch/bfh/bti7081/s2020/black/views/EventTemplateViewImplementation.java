package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.presenters.EventTemplatePresenter;


@Route(value = "EventTemplateView", layout = MainView.class)
public class EventTemplateViewImplementation extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	private EventTemplatePresenter eventTemplatePresenter = new EventTemplatePresenter(this);
	private Dialog dialogCreateEvent;
	private final VerticalLayout contentLayoutFirstRow;
	private final VerticalLayout contentLayoutSecondRow;
	private ArrayList<EventTemplate> eventTemplates;
	private Dialog dialogShowTemplate;

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
		Grid.Column<EventTemplate> descriptionColumn = grid.addColumn(EventTemplate::getDescription).setHeader("Description");
		Grid.Column<EventTemplate> tagColumn = grid.addColumn(EventTemplate::getTags).setHeader("Tags");
//		Grid.Column<EventTemplate> tagColumn;
//		for (Tag t : eventTemplates.getTags()) {
//		grid.addColumn(EventTemplate::getTags).setHeader("Tags");
//		}
		
		Grid.Column<EventTemplate> ratingColumn = grid.addColumn(EventTemplate::getAvgRating).setHeader("Rating");
		grid.addComponentColumn(item -> createUseAsTemplateButton(grid, item)).setHeader("Use as template");
		descriptionColumn.setFlexGrow(3);

		grid.addSelectionListener(event -> {
			Set<EventTemplate> temp = event.getAllSelectedItems();
			createDialogBoxForTemplate(temp.iterator().next());
		});

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
		ArrayList<String> tagList = new ArrayList<>();
//		tagList = 
		tagField.addValueChangeListener(event -> dataProvider.addFilter(eventTemplate -> StringUtils.containsIgnoreCase(eventTemplate.getTags().toString(), tagField.getValue())));

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

	private void createDialogBoxForTemplate(EventTemplate singleTemplate) {
		dialogShowTemplate = new Dialog();
		dialogShowTemplate.setSizeFull();

		VerticalLayout templates = new VerticalLayout();
		templates.setWidth("100%");
		templates.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
//		templates.getStyle().set("border", "1px solid black");
		templates.getStyle().set("overflowY", "auto");
		templates.getStyle().set("display", "block");

		VerticalLayout layout = new VerticalLayout();
		layout.getStyle().set("border", "1px solid #2f6f91");
		layout.getStyle().set("margin", "2px");

		TextField title = new TextField();
		title.setSizeFull();
		title.setValue(singleTemplate.getTitle());
		title.setReadOnly(true);

		TextArea description = new TextArea();
		description.setSizeFull();
		description.setValue(singleTemplate.getDescription());
		description.setReadOnly(true);

		MultiSelectListBox<Tag> tags = new MultiSelectListBox<Tag>();
		tags.setItems(singleTemplate.getTags());
		tags.setReadOnly(true);

		ProgressBar progressBar = new ProgressBar();
		progressBar.setValue(singleTemplate.getAvgRating() / 10);

		Button button = new Button("USE AS TEMPLATE");
		button.addClickListener(
				event -> getUI().ifPresent(ui -> ui.navigate("CreateEvent/" + singleTemplate.getTemplateIDforURL())));

		layout.add(title, description, tags, progressBar, button);
		templates.add(layout);

		// currently all templates are displayed when selecting one row of the grid
		dialogShowTemplate.add(templates);
		dialogShowTemplate.open();
	}
}
