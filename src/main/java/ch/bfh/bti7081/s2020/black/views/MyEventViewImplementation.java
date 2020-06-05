package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.Tag;
public class MyEventViewImplementation<T extends EventViewInterface> extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private VerticalLayout eventLayout = new VerticalLayout();

	public MyEventViewImplementation(T presenter) {
		setSizeFull();
		getStyle().set("align-items", "center");
		getStyle().set("overflowX", "hidden");
		Label labelMyEvents = new Label("My Events: ");
		labelMyEvents.getStyle().set("font-size", "24px");
		labelMyEvents.getStyle().set("font-weight", "bold");
		eventLayout.setWidth("35%");
		eventLayout.setMaxHeight("500px");
		eventLayout.getStyle().set("overflowY", "auto");
		eventLayout.getStyle().set("display", "block");

		for (Event e: presenter.getMyEvents()) {
			
			String participantsHelper = new String(e.getParticipants().toString());
			participantsHelper = participantsHelper.substring(1, participantsHelper.length() - 1);
			participantsHelper = participantsHelper.replaceAll(", ", "\n");
			VerticalLayout layout = new VerticalLayout();
			layout.getStyle().set("border", "2px solid #2f6f91");

			TextField title = new TextField();
			title.setSizeFull();
			title.setValue(e.getEventTemplate().getTitle());
			title.setReadOnly(true);

			TextArea description = new TextArea();
			description.setSizeFull();
			description.setValue(e.getEventTemplate().getDescription());
			description.setReadOnly(true);

			MultiSelectListBox<Tag> tags = new MultiSelectListBox<Tag>();
			tags.setItems(e.getEventTemplate().getTags());
			tags.setReadOnly(true);

			ProgressBar progressBar = new ProgressBar();
			progressBar.setValue(e.getEventTemplate().getAvgRating() / 10);

			HorizontalLayout buttonLayout = new HorizontalLayout();
			
			Button buttonChat = new Button("CHAT");
			buttonChat.addClickListener(event ->
			presenter.buttonClick(EventViewInterface.EventAction.OPENCHAT, e));
			
			Button buttonDetails = new Button("DETAILS");
			buttonDetails.addClickListener(event -> 
			presenter.buttonClick(EventViewInterface.EventAction.DETAILS, e));
			
			Button buttonMarkDone = new Button("EVENT DONE");
			buttonMarkDone.addClickListener(event -> 
			presenter.buttonClick(EventViewInterface.EventAction.MARKDONE, e));
			buttonMarkDone.setWidth("100%");
			
			buttonLayout.add(buttonChat, buttonDetails);

			TextArea participants = new TextArea();
			participants.setSizeFull();
			participants.setValue(participantsHelper);
			participants.setReadOnly(true);
			layout.add(title, description, tags, progressBar, participants, buttonLayout, buttonMarkDone);
			layout.getStyle().set("padding", "5px");
			layout.getStyle().set("margin-left", "2px");
			layout.getStyle().set("margin-right", "2px");
			layout.setMinWidth("350px");

			eventLayout.add(layout);
		}
		
		Label labelMyPastEvents = new Label("My Past Events: ");
		labelMyPastEvents.getStyle().set("font-size", "24px");
		labelMyPastEvents.getStyle().set("font-weight", "bold");
		
		Grid<Event> grid = new Grid<>();
		ListDataProvider<Event> dataProvider = new ListDataProvider<>(presenter.getMyEvents());
		grid.setDataProvider(dataProvider);

		Grid.Column<Event> titleColumn = grid.addColumn(event -> event.getEventTemplate().getTitle()).setHeader("Title");
		Grid.Column<Event> descriptionColumn = grid.addColumn(event -> event.getEventTemplate().getDescription())
				.setHeader("Description");
		Grid.Column<Event> tagColumn = grid.addColumn(event -> event.getEventTemplate().getTags().toString().replaceAll("\\[|\\]", "")).setHeader("Tags");
		Grid.Column<Event> paricipantsColumn = grid.addColumn(event -> event.getParticipants().toString().replaceAll("\\[|\\]", "")).setHeader("Participants");
		descriptionColumn.setFlexGrow(3);

		grid.addSelectionListener(event -> {

		});

		HeaderRow filterRow = grid.appendHeaderRow();
		
		// First filter for rating
		TextField titleField = new TextField();
		titleField.addValueChangeListener(event -> dataProvider.addFilter(publicEvent -> StringUtils
				.containsIgnoreCase(publicEvent.getEventTemplate().getTitle(), titleField.getValue())));

		titleField.setValueChangeMode(ValueChangeMode.EAGER);
		filterRow.getCell(titleColumn).setComponent(titleField);
		titleField.setSizeFull();
		titleField.setPlaceholder("Filter");

		// Second Filter for description
		TextField descriptionField = new TextField();
		descriptionField.addValueChangeListener(event -> dataProvider.addFilter(publicEvent -> StringUtils
				.containsIgnoreCase(publicEvent.getEventTemplate().getDescription(), descriptionField.getValue())));

		descriptionField.setValueChangeMode(ValueChangeMode.EAGER);
		filterRow.getCell(descriptionColumn).setComponent(descriptionField);
		descriptionField.setSizeFull();
		descriptionField.setPlaceholder("Filter");

		// Thrid Filter for tags
		TextField tagField = new TextField();
		ArrayList<String> tagList = new ArrayList<>();
		tagField.addValueChangeListener(event -> dataProvider.addFilter(publicEvent -> StringUtils
				.containsIgnoreCase(publicEvent.getEventTemplate().getTags().toString(), tagField.getValue())));

		tagField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(tagColumn).setComponent(tagField);
		tagField.setSizeFull();
		tagField.setPlaceholder("Filter");
		
		// Fourth Filter for participants
		TextField participantsField = new TextField();
		ArrayList<String> participantsList = new ArrayList<>();
		participantsField.addValueChangeListener(event -> dataProvider.addFilter(publicEvent -> StringUtils
				.containsIgnoreCase(publicEvent.getParticipants().toString(), tagField.getValue())));

		participantsField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(tagColumn).setComponent(tagField);
		participantsField.setSizeFull();
		participantsField.setPlaceholder("Filter");

		grid.setWidth("100%");
		grid.setHeight("400px");
		grid.getStyle().set("overflowY", "auto");
		
		add(labelMyEvents, eventLayout, labelMyPastEvents, grid);
	}
}
