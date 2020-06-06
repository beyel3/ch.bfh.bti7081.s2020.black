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
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventTemplateInterface;
import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.JoinPublicEventInterface;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;

public class JoinPublicEventViewImplementation<T extends JoinPublicEventInterface> extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	private T presenter;
	private Dialog dialogShowEventDetails;

	public JoinPublicEventViewImplementation(T presenter) {
		
		this.presenter = presenter;
		setSizeFull();

		Grid<Event> grid = new Grid<>();
		ListDataProvider<Event> dataProvider = new ListDataProvider<>(presenter.getOpenPublicEvents());
		grid.setDataProvider(dataProvider);

		Grid.Column<Event> titleColumn = grid.addColumn(event -> event.getEventTemplate().getTitle()).setHeader("Title");
		Grid.Column<Event> descriptionColumn = grid.addColumn(event -> event.getEventTemplate().getDescription())
				.setHeader("Description");
		Grid.Column<Event> tagColumn = grid.addColumn(event -> event.getEventTemplate().getTags().toString().replaceAll("\\[|\\]", "")).setHeader("Tags");
		Grid.Column<Event> paricipantsColumn = grid.addColumn(event -> event.getParticipants().toString()).setHeader("Participants");
		Grid.Column<Event> maxParticipantsColumn = grid.addColumn(Event::getMaxParticipants).setHeader("Max Participants");
		grid.addComponentColumn(item -> createJoinPublicEventButton(item)).setHeader("Join Public Event");
		descriptionColumn.setFlexGrow(3);

		grid.addSelectionListener(event -> {
			Set<Event> temp = event.getAllSelectedItems();
			createDetailDialogBoxForPublicEvent(temp.iterator().next());
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
		tagField.addValueChangeListener(event -> dataProvider.addFilter(publicEvent -> StringUtils
				.containsIgnoreCase(publicEvent.getParticipants().toString(), tagField.getValue())));

		tagField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(tagColumn).setComponent(tagField);
		tagField.setSizeFull();
		tagField.setPlaceholder("Filter");

		grid.setWidth("100%");
		grid.setHeight("68vh");
		grid.getStyle().set("overflowY", "auto");

		add(grid);
	}

	private Button createJoinPublicEventButton(Event item) {
		Button buttonJoinOpenEvent = new Button("JOIN");
		buttonJoinOpenEvent.addClickListener(event -> presenter.selectEvent(item));
		return buttonJoinOpenEvent;
	}
	
	private void createDetailDialogBoxForPublicEvent(Event singleEvent) {
		dialogShowEventDetails = new Dialog();
		dialogShowEventDetails.setSizeFull();

		VerticalLayout events = new VerticalLayout();
		events.setWidth("100%");
		events.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
//		templates.getStyle().set("border", "1px solid black");
		events.getStyle().set("overflowY", "auto");
		events.getStyle().set("display", "block");
		String participantsHelper = new String(singleEvent.getParticipants().toString());
		participantsHelper = participantsHelper.substring(1, participantsHelper.length() - 1);
		participantsHelper = participantsHelper.replaceAll(", ", "\n");

		VerticalLayout layout = new VerticalLayout();
		layout.getStyle().set("border", "1px solid #2f6f91");
		layout.getStyle().set("margin", "2px");

		TextField title = new TextField();
		title.setSizeFull();
		title.setValue(singleEvent.getEventTemplate().getTitle());
		title.setReadOnly(true);

		TextArea description = new TextArea();
		description.setSizeFull();
		description.setValue(singleEvent.getEventTemplate().getDescription());
		description.setReadOnly(true);

		MultiSelectListBox<Tag> tags = new MultiSelectListBox<Tag>();
		tags.setItems(singleEvent.getEventTemplate().getTags());
		tags.setReadOnly(true);

		ProgressBar progressBar = new ProgressBar();
		progressBar.setValue(singleEvent.getEventTemplate().getAvgRating() / 10);
		
		TextArea participants = new TextArea();
		participants.setSizeFull();
		participants.setValue(participantsHelper);
		participants.setReadOnly(true);
		
		layout.add(title, description, tags, progressBar, participants, createJoinPublicEventButton(singleEvent));
		layout.setMinWidth("350px");
		events.add(layout);

		dialogShowEventDetails.add(events);
		dialogShowEventDetails.open();
	}
}
