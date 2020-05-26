package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventTemplateInterface;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;

public class OpenPublicEventsViewImplementation<T extends EventTemplateInterface> extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private T presenter;
	private ArrayList<Event> openPublicEvents;

	public OpenPublicEventsViewImplementation(T presenter, ArrayList<Event> openPublicEvents) {

		this.presenter = presenter;
		this.openPublicEvents = openPublicEvents;

		setSizeFull();

		Grid<Event> grid = new Grid<>();
		ListDataProvider<Event> dataProvider = new ListDataProvider<>(openPublicEvents);
		grid.setDataProvider(dataProvider);

		Grid.Column<Event> titleColumn = grid.addColumn(event -> event.getEventTemplate().getTitle()).setHeader("Title");
		Grid.Column<Event> descriptionColumn = grid.addColumn(event -> event.getEventTemplate().getDescription())
				.setHeader("Description");
		Grid.Column<Event> tagColumn = grid.addColumn(event -> event.getEventTemplate().getTags().toString().replaceAll("[[]]","")).setHeader("Tags");
		Grid.Column<Event> paricipantsColumn = grid.addColumn(event -> event.getParticipants().toString().replaceAll("[[]]",""))
				.setHeader("Participants");
		Grid.Column<Event> maxParticipantsColumn = grid.addColumn(Event::getMaxParticipants).setHeader("Max Participants");
		grid.addComponentColumn(item -> createJoinPublicEventButton(item)).setHeader("Join pulibc Event");
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
		//buttonJoinOpenEvent.addClickListener(event -> presenter.joinPublicEvent(item));
		return buttonJoinOpenEvent;
	}
}
